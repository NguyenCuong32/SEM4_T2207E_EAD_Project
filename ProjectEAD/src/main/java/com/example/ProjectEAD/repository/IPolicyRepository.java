package com.example.ProjectEAD.repository;

import com.example.ProjectEAD.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPolicyRepository extends JpaRepository<Policy, Integer> {
}
