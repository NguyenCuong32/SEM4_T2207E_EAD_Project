package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.UserCreateDTO;
import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.dto.UserDetailDTO;
import com.fai.brofee_fe.entity.User;
import com.fai.brofee_fe.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.fai.brofee_fe.entity.stored_procedure_entity.User_SP;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void createUser(UserCreateDTO userCreateDTO);

    Optional<UserDTO> getUserByCodeOrPhone(String codeOrPhone);

    List<User_SP> getUsersWithPagination(int size, int page, String searchTerm);

    UserDetailDTO getUserByCode(String code);

    List<UserHierarchyItem_SP> getParentUsers(String code);

    List<UserHierarchyItem_SP> getChildrenUsers(String code);
}
