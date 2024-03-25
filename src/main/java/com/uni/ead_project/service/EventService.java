package com.uni.ead_project.service;

import com.uni.ead_project.entity.EventEntity;
import com.uni.ead_project.repository.EventsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService{
    private final EventsRepository eventsRepository;

    public EventService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<EventEntity> getAllEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public Optional<EventEntity> getEventById(String eventId) {
        return eventsRepository.findById(eventId);
    }

    @Override
    @Transactional
    public void saveEvent(EventEntity eventEntity) {
        eventsRepository.save(eventEntity);
    }

    @Override
    public void deleteEvent(String eventId) {
        eventsRepository.deleteById(eventId);
    }
}
