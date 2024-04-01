package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Partners", schema = "dbo", catalog = "lab")
public class PartnerEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private String userId;
    @PrePersist
    public void generateId() {
        this.userId = UUID.randomUUID().toString();
    }
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "user_qr")
    private String userQr;
    @Basic
    @Column(name = "inviter_id")
    private String inviterId;
    @Basic
    @Column(name = "inviter_phone_number")
    private String inviterPhoneNumber;
    @Basic
    @Column(name = "inviter_email")
    private String inviterEmail;

    @Override
    public String toString() {
        return "PartnersEntity{" +
                "userId='" + userId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userQr='" + userQr + '\'' +
                ", inviterId='" + inviterId + '\'' +
                ", inviterPhoneNumber='" + inviterPhoneNumber + '\'' +
                ", inviterEmail='" + inviterEmail + '\'' +
                '}';
    }

    public PartnerEntity() {

    }

//    @OneToOne
//    @JoinColumn(name = "UserID", referencedColumnName = "UserID", nullable = false)
//    private UsersEntity usersByUserId;
//    @ManyToOne
//    @JoinColumn(name = "InviterID", referencedColumnName = "UserID")
//    private UsersEntity usersByInviterId;
//    @ManyToOne
//    @JoinColumn(name = "EventID", referencedColumnName = "EventID")
//    private EventsEntity eventsByEventId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserQr() {
        return userQr;
    }

    public void setUserQr(String userQr) {
        this.userQr = userQr;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getInviterPhoneNumber() {
        return inviterPhoneNumber;
    }

    public void setInviterPhoneNumber(String inviterPhoneNumber) {
        this.inviterPhoneNumber = inviterPhoneNumber;
    }

    public String getInviterEmail() {
        return inviterEmail;
    }

    public void setInviterEmail(String inviterEmail) {
        this.inviterEmail = inviterEmail;
    }

    public PartnerEntity(String userId, String phoneNumber, String email, String userQr, String inviterId, String inviterPhoneNumber, String inviterEmail) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userQr = userQr;
        this.inviterId = inviterId;
        this.inviterPhoneNumber = inviterPhoneNumber;
        this.inviterEmail = inviterEmail;
    }


//    public UsersEntity getUsersByUserId() {
//        return usersByUserId;
//    }
//
//    public void setUsersByUserId(UsersEntity usersByUserId) {
//        this.usersByUserId = usersByUserId;
//    }
//
//    public UsersEntity getUsersByInviterId() {
//        return usersByInviterId;
//    }
//
//    public void setUsersByInviterId(UsersEntity usersByInviterId) {
//        this.usersByInviterId = usersByInviterId;
//    }
//
//    public EventsEntity getEventsByEventId() {
//        return eventsByEventId;
//    }
//
//    public void setEventsByEventId(EventsEntity eventsByEventId) {
//        this.eventsByEventId = eventsByEventId;
//    }
}
