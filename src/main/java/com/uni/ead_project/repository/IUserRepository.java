package com.uni.ead_project.repository;

import com.uni.ead_project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <UserEntity, String> {

}
