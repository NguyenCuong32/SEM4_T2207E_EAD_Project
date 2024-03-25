package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Events", schema = "dbo", catalog = "lab")
public class EventEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "event_id")
    private String eventId;
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
//    @ManyToOne
//    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID")
//    private ServicesEntity servicesByServiceId;
//    @OneToMany(mappedBy = "eventsByEventId")
//    private Collection<PartnersEntity> partnersByEventId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(eventName, that.eventName) && Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(value, that.value) && Objects.equals(scopeApply, that.scopeApply) && Objects.equals(bannerImage, that.bannerImage) && Objects.equals(eventStatus, that.eventStatus) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, startDateTime, endDateTime, shortDescription, value, scopeApply, bannerImage, eventStatus, serviceId);
    }

//    public ServicesEntity getServicesByServiceId() {
//        return servicesByServiceId;
//    }
//
//    public void setServicesByServiceId(ServicesEntity servicesByServiceId) {
//        this.servicesByServiceId = servicesByServiceId;
//    }

//    public Collection<PartnersEntity> getPartnersByEventId() {
//        return partnersByEventId;
//    }
//
//    public void setPartnersByEventId(Collection<PartnersEntity> partnersByEventId) {
//        this.partnersByEventId = partnersByEventId;
//    }
}
