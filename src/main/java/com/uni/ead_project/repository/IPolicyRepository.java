package com.uni.ead_project.repository;

import com.uni.ead_project.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPolicyRepository extends JpaRepository<PolicyEntity,String> {
}
