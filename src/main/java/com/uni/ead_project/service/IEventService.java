package com.uni.ead_project.service;

import com.uni.ead_project.entity.EventEntity;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<EventEntity> getAllEvents();
    Optional<EventEntity> getEventById(String eventId);
    void saveEvent(EventEntity event);
    void updateEvent();
    void deleteEvent(String eventId);
}
