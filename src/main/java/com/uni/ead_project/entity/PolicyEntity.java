package com.uni.ead_project.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Policies", schema = "dbo", catalog = "lab")
public class PolicyEntity {
    @Basic
    @Column(name = "policy_id")
    private String policyId;
    @Basic
    @Column(name = "start_date_time")
    private Date startDateTime;
    @Basic
    @Column(name = "end_date_time")
    private Date endDateTime;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id")
    private String serviceId;
    @Basic
    @Column(name = "policy_formula")
    private String policyFormula;
    @Basic
    @Column(name = "facility_apply")
    private String facilityApply;
    @Basic
    @Column(name = "status")
    private Byte status;
//    @OneToOne
//    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID", nullable = false)
//    private ServicesEntity servicesByServiceId;

    public PolicyEntity(String policyId, Date startDateTime, Date endDateTime, String serviceId, String policyFormula, String facilityApply, Byte status) {
        this.policyId = policyId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.serviceId = serviceId;
        this.policyFormula = policyFormula;
        this.facilityApply = facilityApply;
        this.status = status;
    }

    public PolicyEntity() {

    }

    @Override
    public String toString() {
        return "PoliciesEntity{" +
                "policyId='" + policyId + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", serviceId='" + serviceId + '\'' +
                ", policyFormula='" + policyFormula + '\'' +
                ", facilityApply='" + facilityApply + '\'' +
                ", status=" + status +
                '}';
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPolicyFormula() {
        return policyFormula;
    }

    public void setPolicyFormula(String policyFormula) {
        this.policyFormula = policyFormula;
    }

    public String getFacilityApply() {
        return facilityApply;
    }

    public void setFacilityApply(String facilityApply) {
        this.facilityApply = facilityApply;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
    //    public ServicesEntity getServicesByServiceId() {
//        return servicesByServiceId;
//    }
//
//    public void setServicesByServiceId(ServicesEntity servicesByServiceId) {
//        this.servicesByServiceId = servicesByServiceId;
//    }
}
