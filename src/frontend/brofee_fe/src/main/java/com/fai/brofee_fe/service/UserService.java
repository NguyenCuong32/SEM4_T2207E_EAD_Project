package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.UserCreateDTO;
import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.dto.UserDetailDTO;
import com.fai.brofee_fe.entity.User;
import com.fai.brofee_fe.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.fai.brofee_fe.entity.stored_procedure_entity.User_SP;
import com.fai.brofee_fe.repository.UserRepository;
import com.fai.brofee_fe.repository.stored_procedure.UserHierarchyRepository_SP;
import com.fai.brofee_fe.repository.stored_procedure.UserRepository_SP;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final UserRepository_SP userRepositorySP;
    private final UserHierarchyRepository_SP userHierarchyRepositorySP;
    private final ModelMapper modelMapper;

    @Override
    public void createUser(UserCreateDTO userCreateDTO) {

    }

    @Override
    public Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone) {
        return userRepository.findByCodeOrPhone(codeOrPhone, codeOrPhone).map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    @Transactional
    public List<User_SP> getUsersWithPagination(int size, int page, String searchTerm) {
        return userRepositorySP.getUsersWithPagination(size, page, searchTerm);
    }

    @Override
    public UserDetailDTO getUserByCode(String code) {
        return userRepository.findByCode(code).map(user -> modelMapper.map(user, UserDetailDTO.class)).orElse(null);
    }

    @Override
    @Transactional
    public List<UserHierarchyItem_SP> getParentUsers(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElse(null);
        return userHierarchyRepositorySP.getParentUsers(id);
    }

    @Override
    @Transactional
    public List<UserHierarchyItem_SP> getChildrenUsers(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElse(null);
        return userHierarchyRepositorySP.getChildrenUsers(id);
    }
}
