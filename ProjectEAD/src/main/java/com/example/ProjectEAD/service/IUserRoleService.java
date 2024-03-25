package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.UserRole;

public interface IUserRoleService {
    void add(UserRole userRole);
    void deleteById(Integer id);
}
