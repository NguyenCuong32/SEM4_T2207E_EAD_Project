package com.uni.ead_project.service;

import com.uni.ead_project.entity.CouponEntity;
import com.uni.ead_project.repository.CouponsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponsService implements ICouponsService{
    private final CouponsRepository couponsRepository;

    public CouponsService(CouponsRepository couponsRepository) {
        this.couponsRepository = couponsRepository;
    }

    @Override
    public List<CouponEntity> getAllCoupons() {
        return couponsRepository.findAll();
    }

    @Override
    public Optional<CouponEntity> getCouponById(String couponId) {
        return couponsRepository.findById(couponId);
    }

    @Override
    @Transactional
    public void saveCoupon(CouponEntity couponEntity) {
        couponsRepository.save(couponEntity);
    }

    @Override
    public void upadateCoupon() {

    }

    @Override
    public void deleteCoupon(String couponId) {
        couponsRepository.deleteById(couponId);
    }
}
