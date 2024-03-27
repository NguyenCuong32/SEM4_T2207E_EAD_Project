package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Policy;

import java.util.List;
import java.util.Optional;


public interface IPolicyService {
    List<Policy> getAllPolicy();
    Optional<Policy> getPolicyById(Integer id);

    void add(Policy policy);
    void deleteById(Integer id);
}
