package com.uni.ead_project.service;

import com.uni.ead_project.entity.PolicyEntity;

import java.util.List;
import java.util.Optional;

public interface IPolicyService {
    List<PolicyEntity> getAllPolicies();
    Optional<PolicyEntity>getPolicyById(String policyId);
    void savePolicy(PolicyEntity policy);
    void updatePolicy();
    void deletePolicyId(String policyId);
}
