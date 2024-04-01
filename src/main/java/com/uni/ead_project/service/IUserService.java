package com.uni.ead_project.service;

import com.uni.ead_project.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserEntity>getAllUsers();
    Optional<UserEntity>getUserById(String userId);
    void saveUser(UserEntity user);
    void updateUser();
    void deleteUser(String userId);
}
