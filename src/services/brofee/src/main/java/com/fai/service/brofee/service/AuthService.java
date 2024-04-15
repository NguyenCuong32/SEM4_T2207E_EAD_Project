package com.fai.service.brofee.service;

import com.fai.service.brofee.dto.*;
import com.fai.service.brofee.entity.Role;
import com.fai.service.brofee.entity.User;
import com.fai.service.brofee.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.fai.service.brofee.repository.RoleRepository;
import com.fai.service.brofee.repository.UserRepository;
import com.fai.service.brofee.repository.stored_procedure.UserHierarchyRepository_SP;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UserHierarchyRepository_SP userHierarchyRepositorySP;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone) {
        return userRepository.findByCodeOrPhone(codeOrPhone, codeOrPhone).map(user -> modelMapper.map(user, UserDTO.class));
    }

    public List<UserHierarchyItem_SP> getParentUsers(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElse(null);
        return userHierarchyRepositorySP.getParentUsers(id);
    }

    public List<UserHierarchyItem_SP> getChildrenUsers(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElse(null);
        return userHierarchyRepositorySP.getChildrenUsers(id);
    }

    public void signup(UserCreateDTO dto) {
        // Check if email exists
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        // Check if phone number exists
        if (userRepository.existsByPhone(dto.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }
        // Create new user object
        String code;
        do {
            code = generateCode();
        } while (userRepository.existsByCode(code));
        User user = modelMapper.map(dto, User.class);
        user.setCode(code);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        if(dto.getReferrerCodeOrPhone() != null) {
            User parent = userRepository.findByCodeOrPhone(dto.getReferrerCodeOrPhone(), dto.getReferrerCodeOrPhone()).orElseThrow(
                    () -> new EntityNotFoundException("User with code/phone does not exist.")
            );
            user.setReferrer(parent);
        }

        Role role = roleRepository.findByName("CUSTOMER").orElseThrow(
                () -> new EntityNotFoundException("Role with name 'CUSTOMER' does not exist.")
        );
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        // 1. Authenticate the user
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        // 2. Generate JWT token with necessary claims
        String token = jwtService.generateToken(authenticate);

        // 3. Construct response with token information
        return AuthenticationResponse.builder()
                .accessToken(token)
                .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                .email(loginRequest.getEmail())
                .build();
    }

    public UserEditDTO getUserEditDTOById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id does not exist.")
        );
        return modelMapper.map(user, UserEditDTO.class);
    }

    @Transactional
    public void updateUser(Long id, UserEditDTO userEditDTO) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id does not exist.")
        );

        if (emailExists(userEditDTO.getEmail()) && !user.getEmail().equals(userEditDTO.getEmail())) {
            throw new EntityExistsException("There is an account with that email.");
        }
        if (phoneExists(userEditDTO.getPhone()) && !user.getPhone().equals(userEditDTO.getPhone())) {
            throw new EntityExistsException("There is an account with that phone number.");
        }

        user.setName(userEditDTO.getName());
        user.setEmail(userEditDTO.getEmail());
        user.setPhone(userEditDTO.getPhone());
        user.setAddress(userEditDTO.getAddress());
        userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean phoneExists(String phone) {
        return userRepository.findByPhone(phone).isPresent();
    }

    private String generateCode() {
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();

        // Ensure a mix of numbers and letters
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                // Add a random uppercase letter
                codeBuilder.append((char) (random.nextInt(26) + 'A'));
            } else {
                // Add a random digit
                codeBuilder.append(random.nextInt(10));
            }
        }

        return codeBuilder.toString();
    }
}
