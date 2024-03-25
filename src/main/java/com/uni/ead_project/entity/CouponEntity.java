package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Coupons", schema = "dbo", catalog = "lab")
public class CouponEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "coupon_id")
    private String couponId;
    @Basic
    @Column(name = "coupon_name")
    private String couponName;
    @Basic
    @Column(name = "user_id")
    private String userId;
//    @OneToOne
//    @JoinColumn(name = "CouponID", referencedColumnName = "UserID", nullable = false)
//    private UsersEntity usersByCouponId;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponEntity that = (CouponEntity) o;
        return Objects.equals(couponId, that.couponId) && Objects.equals(couponName, that.couponName) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponId, couponName, userId);
    }

//    public UsersEntity getUsersByCouponId() {
//        return usersByCouponId;
//    }
//
//    public void setUsersByCouponId(UsersEntity usersByCouponId) {
//        this.usersByCouponId = usersByCouponId;
//    }
}
