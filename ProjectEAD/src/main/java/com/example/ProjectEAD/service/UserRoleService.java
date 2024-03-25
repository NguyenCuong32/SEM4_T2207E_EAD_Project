package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.UserRole;
import com.example.ProjectEAD.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService{
    private final IUserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(IUserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void add(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public void deleteById(Integer id) {
        userRoleRepository.deleteById(id);
    }
}
