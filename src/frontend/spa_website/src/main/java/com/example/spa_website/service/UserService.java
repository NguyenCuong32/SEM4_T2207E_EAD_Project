package com.example.spa_website.service;

import com.example.spa_website.dto.UserCreateDTO;
import com.example.spa_website.dto.UserDTO;
import com.example.spa_website.dto.UserDetailDTO;
import com.example.spa_website.dto.UserEditDTO;
import com.example.spa_website.entity.Role;
import com.example.spa_website.entity.User;
import com.example.spa_website.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.example.spa_website.entity.stored_procedure_entity.User_SP;
import com.example.spa_website.repository.RoleRepository;
import com.example.spa_website.repository.UserRepository;
import com.example.spa_website.repository.stored_procedure_repository.UserHierarchyRepository_SP;
import com.example.spa_website.repository.stored_procedure_repository.UserRepository_SP;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRepository_SP userRepositorySP;
    private final UserHierarchyRepository_SP userHierarchyRepositorySP;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone) {
        return userRepository.findByCodeOrPhone(codeOrPhone, codeOrPhone).map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Transactional
    public List<User_SP> getUsersWithPagination(int size, int page, String searchTerm) {
        return userRepositorySP.getUsersWithPagination(size, page, searchTerm);
    }

    public UserDetailDTO getUserByCode(String code) {
        return userRepository.findByCode(code).map(user -> modelMapper.map(user, UserDetailDTO.class)).orElse(null);
    }

    @Transactional
    public List<UserHierarchyItem_SP> getParentUsers(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElse(null);
        return userHierarchyRepositorySP.getParentUsers(id);
    }

    @Transactional
    public List<UserHierarchyItem_SP> getChildrenUsers(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElse(null);
        return userHierarchyRepositorySP.getChildrenUsers(id);
    }

    @Transactional
    public void createUser(UserCreateDTO userCreateDTO) {
        if (emailExists(userCreateDTO.getEmail())) {
            throw new EntityExistsException("There is an account with that email.");
        }
        if (phoneExists(userCreateDTO.getPhone())) {
            throw new EntityExistsException("There is an account with that phone number.");
        }

        // Create new user object
        String code;
        do {
            code = generateCode();
        } while (userRepository.existsByCode(code));
        User user = modelMapper.map(userCreateDTO, User.class);
        user.setCode(code);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        if(userCreateDTO.getReferrerCodeOrPhone() != null) {
            User parent = userRepository.findByCodeOrPhone(userCreateDTO.getReferrerCodeOrPhone(), userCreateDTO.getReferrerCodeOrPhone()).orElseThrow(
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
