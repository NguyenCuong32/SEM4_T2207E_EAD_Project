package com.uni.ead_project.service;

import com.uni.ead_project.entity.PolicyEntity;

import java.util.List;
import java.util.Optional;

public interface IPoliciesService {
    List<PolicyEntity> getAllPolicies();
    Optional<PolicyEntity>getPolicyById(String policyId);
    void savePolicyId(PolicyEntity policyEntity);
    void deletePolicyId(String policyId);
}
