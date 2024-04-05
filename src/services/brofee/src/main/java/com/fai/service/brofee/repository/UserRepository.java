package com.fai.service.brofee.repository;

import com.fai.service.brofee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findByCodeOrPhone(String code, String phone);

    Optional<User> findByCode(String code);

    Boolean existsByCode(String code);

}
