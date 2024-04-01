package com.uni.ead_project.service;

import com.uni.ead_project.entity.UserEntity;
import com.uni.ead_project.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository IUserRepository;

    public UserService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return IUserRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(String userId) {
        return IUserRepository.findById(userId);
    }

    @Override
    public void saveUser(UserEntity usersEntity) {
        IUserRepository.save(usersEntity);
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser(String userId) {
        IUserRepository.deleteById(userId);
    }
}
