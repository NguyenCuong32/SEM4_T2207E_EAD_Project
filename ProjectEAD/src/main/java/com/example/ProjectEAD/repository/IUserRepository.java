package com.example.ProjectEAD.repository;

import com.example.ProjectEAD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    public Optional<User> getUserByUsername(String username);
    public Optional<User> getUserByPhone(String phone);
}
