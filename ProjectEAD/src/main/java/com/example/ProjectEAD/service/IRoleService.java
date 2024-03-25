package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> getAllRole();
    Optional<Role> getRoleById(Integer id);
    void add(Role role);
    void deleteById(Integer id);
}
