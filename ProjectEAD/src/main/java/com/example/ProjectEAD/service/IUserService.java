package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUser();
    Optional<User> getUserById(Integer id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByPhone(String phone);
    void add(User user);
    void deleteById(Integer id);
}
