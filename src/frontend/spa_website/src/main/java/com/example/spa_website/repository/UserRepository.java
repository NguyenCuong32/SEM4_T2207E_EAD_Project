package com.example.spa_website.repository;

import com.example.spa_website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findByCodeOrPhone(String code, String phone);

    Optional<User> findByCode(String code);

    Boolean existsByCode(String code);

}
