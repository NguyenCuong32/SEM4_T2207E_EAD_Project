package com.uni.ead_project.service;

import com.uni.ead_project.entity.CouponEntity;

import java.util.List;
import java.util.Optional;

public interface ICouponsService {
    List<CouponEntity> getAllCoupons();
    Optional<CouponEntity> getCouponById(String couponId);
    void saveCoupon(CouponEntity couponEntity);
    void upadateCoupon();
    void deleteCoupon(String couponId);
}
