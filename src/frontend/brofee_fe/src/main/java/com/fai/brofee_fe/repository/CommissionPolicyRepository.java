package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.entity.CommissionPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionPolicyRepository extends JpaRepository<CommissionPolicy, Long> {


}