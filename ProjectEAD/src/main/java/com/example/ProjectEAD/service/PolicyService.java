package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Policy;
import com.example.ProjectEAD.repository.IPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService implements IPolicyService {

    private final IPolicyRepository policyRepository;

    @Autowired
    public PolicyService(IPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }
    @Override
    public List<Policy> getAllPolicy() {
        return policyRepository.findAll();
    }

    @Override
    public Optional<Policy> getPolicyById(Integer id) {
        return policyRepository.findById(id);
    }

    @Override
    public void add(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public void deleteById(Integer id) {
        policyRepository.deleteById(id);
    }
}
