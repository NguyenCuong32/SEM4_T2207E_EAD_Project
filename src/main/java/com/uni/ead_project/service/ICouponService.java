package com.uni.ead_project.service;

import com.uni.ead_project.entity.CouponEntity;

import java.util.List;
import java.util.Optional;

public interface ICouponService {
    List<CouponEntity> getAllCoupons();
    Optional<CouponEntity> getCouponById(String couponId);
    void saveCoupon(CouponEntity coupon);
    void updateCoupon();
    void deleteCoupon(String couponId);
}
