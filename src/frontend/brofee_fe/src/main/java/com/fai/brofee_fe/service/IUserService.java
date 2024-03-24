package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.UserCreateDTO;
import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.dto.UserDetail.RootDetailUserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void createUser(UserCreateDTO userCreateDTO);

    Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone);

    List<UserDTO> SearchUser(int page, int size , String search);

    long CountSearchUser(String search);

    RootDetailUserDTO detailUser(Long id);


}
