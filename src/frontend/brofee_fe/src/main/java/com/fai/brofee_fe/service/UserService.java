package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.UserCreateDTO;
import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.entity.User;
import com.fai.brofee_fe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createUser(UserCreateDTO userCreateDTO) {

    }

    @Override
    public Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone) {
        return userRepository.findByCodeOrPhone(codeOrPhone, codeOrPhone).map(user -> modelMapper.map(user, UserDTO.class));
    }
}
