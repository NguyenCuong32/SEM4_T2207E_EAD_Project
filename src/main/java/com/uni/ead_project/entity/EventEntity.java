package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Events", schema = "dbo", catalog = "lab")
public class EventEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, sequenceName = "my_sequence")
    @Column(name = "event_id")
    private String eventId;
    @PrePersist
    public void generateId() {
        this.eventId = UUID.randomUUID().toString();
    }
    @Basic
    @Column(name = "event_name")
    private String eventName;
    @Basic
    @Column(name = "start_date_time")
    private Date startDateTime;
    @Basic
    @Column(name = "end_date_time")
    private Date endDateTime;
    @Basic
    @Column(name = "short_description")
    private String shortDescription;
    @Basic
    @Column(name = "value")
    private BigDecimal value;
    @Basic
    @Column(name = "scope_apply")
    private String scopeApply;
    @Basic
    @Column(name = "banner_image")
    private String bannerImage;
    @Basic
    @Column(name = "event_status")
    private Boolean eventStatus;
    @Basic
    @Column(name = "service_id")
    private String serviceId;

    public EventEntity(String eventId, String eventName, Date startDateTime, Date endDateTime, String shortDescription, BigDecimal value, String scopeApply, String bannerImage, Boolean eventStatus, String serviceId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.shortDescription = shortDescription;
        this.value = value;
        this.scopeApply = scopeApply;
        this.bannerImage = bannerImage;
        this.eventStatus = eventStatus;
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", shortDescription='" + shortDescription + '\'' +
                ", value=" + value +
                ", scopeApply='" + scopeApply + '\'' +
                ", bannerImage='" + bannerImage + '\'' +
                ", eventStatus=" + eventStatus +
                ", serviceId='" + serviceId + '\'' +
                '}';
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getScopeApply() {
        return scopeApply;
    }

    public void setScopeApply(String scopeApply) {
        this.scopeApply = scopeApply;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Boolean getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Boolean eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public EventEntity() {

    }
}