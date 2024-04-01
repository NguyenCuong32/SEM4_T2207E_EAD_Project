package com.uni.ead_project.service;

import com.uni.ead_project.entity.PolicyEntity;
import com.uni.ead_project.repository.IPolicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService implements IPolicyService {
    private final IPolicyRepository IPolicyRepository;

    public PolicyService(IPolicyRepository IPolicyRepository) {
        this.IPolicyRepository = IPolicyRepository;
    }

    @Override
    public List<PolicyEntity> getAllPolicies() {
        return IPolicyRepository.findAll();
    }

    @Override
    public Optional<PolicyEntity> getPolicyById(String serviceId) {
        return IPolicyRepository.findById(serviceId);
    }

    @Override
    @Transactional
    public void savePolicy(PolicyEntity policyEntity) {
        IPolicyRepository.save(policyEntity);
    }

    @Override
    public void updatePolicy() {

    }

    @Override
    public void deletePolicyId(String policyId) {
        IPolicyRepository.deleteById(policyId);
    }
}
