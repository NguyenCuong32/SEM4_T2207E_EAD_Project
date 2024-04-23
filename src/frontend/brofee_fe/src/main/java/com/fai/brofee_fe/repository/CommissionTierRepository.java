package com.fai.brofee_fe.repository;

import com.fai.brofee_fe.entity.CommissionTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommissionTierRepository extends JpaRepository<CommissionTier, Long> {
}
