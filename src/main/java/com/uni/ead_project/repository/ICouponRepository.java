package com.uni.ead_project.repository;

import com.uni.ead_project.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponRepository extends JpaRepository<CouponEntity, String> {
}
