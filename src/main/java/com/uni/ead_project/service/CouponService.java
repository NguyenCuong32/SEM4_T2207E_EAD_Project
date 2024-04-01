package com.uni.ead_project.service;

import com.uni.ead_project.entity.CouponEntity;
import com.uni.ead_project.repository.ICouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService implements ICouponService {
    private final ICouponRepository ICouponRepository;

    public CouponService(ICouponRepository ICouponRepository) {
        this.ICouponRepository = ICouponRepository;
    }

    @Override
    public List<CouponEntity> getAllCoupons() {
        return ICouponRepository.findAll();
    }

    @Override
    public Optional<CouponEntity> getCouponById(String couponId) {
        return ICouponRepository.findById(couponId);
    }

    @Override
    @Transactional
    public void saveCoupon(CouponEntity couponEntity) {
        ICouponRepository.save(couponEntity);
    }

    @Override
    public void updateCoupon() {

    }

    @Override
    public void deleteCoupon(String couponId) {
        ICouponRepository.deleteById(couponId);
    }
}
