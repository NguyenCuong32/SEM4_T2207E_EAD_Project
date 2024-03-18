package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findByCodeOrPhone(String code, String phone);

    Boolean existsByCode(String code);

}
