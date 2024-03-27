package com.example.ProjectEAD.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_at")
    private Date startAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_at")
    private Date endAt;
    @Column(name = "thumb_banner")
    private String thumbBanner;
    private Integer status;
    private String description;

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getThumbBanner() {
        return thumbBanner;
    }

    public void setThumbBanner(String thumbBanner) {
        this.thumbBanner = thumbBanner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}