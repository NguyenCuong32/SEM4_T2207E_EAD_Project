package com.example.ProjectEAD.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "facilityEvent")
public class FacilityEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Integer status;

    public FacilityEvent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
