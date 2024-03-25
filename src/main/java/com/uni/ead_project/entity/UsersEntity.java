package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Users", schema = "dbo", catalog = "lab")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "fullname")
    private String fullname;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "rank")
    private Integer rank;
//    @OneToOne(mappedBy = "usersByCouponId")
//    private CouponsEntity couponsByUserId;
//    @OneToOne(mappedBy = "usersByUserId")
//    private PartnersEntity partnersByUserId;
//    @OneToMany(mappedBy = "usersByInviterId")
//    private Collection<PartnersEntity> partnersByUserId_0;
//    @OneToMany(mappedBy = "usersByUserId")
//    private Collection<RolesEntity> rolesByUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(fullname, that.fullname) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, fullname, phoneNumber, email, address, gender, rank);
    }

//    public CouponsEntity getCouponsByUserId() {
//        return couponsByUserId;
//    }
//
//    public void setCouponsByUserId(CouponsEntity couponsByUserId) {
//        this.couponsByUserId = couponsByUserId;
//    }
//
//    public PartnersEntity getPartnersByUserId() {
//        return partnersByUserId;
//    }
//
//    public void setPartnersByUserId(PartnersEntity partnersByUserId) {
//        this.partnersByUserId = partnersByUserId;
//    }
//
//    public Collection<PartnersEntity> getPartnersByUserId_0() {
//        return partnersByUserId_0;
//    }
//
//    public void setPartnersByUserId_0(Collection<PartnersEntity> partnersByUserId_0) {
//        this.partnersByUserId_0 = partnersByUserId_0;
//    }
//
//    public Collection<RolesEntity> getRolesByUserId() {
//        return rolesByUserId;
//    }
//
//    public void setRolesByUserId(Collection<RolesEntity> rolesByUserId) {
//        this.rolesByUserId = rolesByUserId;
//    }
}
