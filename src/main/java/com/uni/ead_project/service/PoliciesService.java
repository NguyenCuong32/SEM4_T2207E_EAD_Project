package com.uni.ead_project.service;

import com.uni.ead_project.entity.PoliciesEntity;
import com.uni.ead_project.repository.PoliciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliciesService implements IPoliciesService{
    private final PoliciesRepository policiesRepository;

    public PoliciesService(PoliciesRepository policiesRepository) {
        this.policiesRepository = policiesRepository;
    }

    @Override
    public List<PoliciesEntity> getAllPolicies() {
        return policiesRepository.findAll();
    }

    @Override
    public Optional<PoliciesEntity> getPolicyById(String serviceId) {
        return policiesRepository.findById(serviceId);
    }

    @Override
    @Transactional
    public void savePolicyId(PoliciesEntity policiesEntity) {
        policiesRepository.save(policiesEntity);
    }

    @Override
    public void deletePolicyId(String policyId) {
        policiesRepository.deleteById(policyId);
    }
}
