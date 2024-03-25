package com.example.ProjectEAD.repository;

import com.example.ProjectEAD.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
}
