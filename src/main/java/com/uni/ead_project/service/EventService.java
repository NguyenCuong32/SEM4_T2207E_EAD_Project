package com.uni.ead_project.service;

import com.uni.ead_project.entity.EventEntity;
import com.uni.ead_project.repository.IEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IEventService{
    private final IEventRepository IEventRepository;

    public EventService(IEventRepository IEventRepository) {
        this.IEventRepository = IEventRepository;
    }

    @Override
    public List<EventEntity> getAllEvents() {
        return IEventRepository.findAll();
    }

    @Override
    public Optional<EventEntity> getEventById(String eventId) {
        return IEventRepository.findById(eventId);
    }

    @Override
    @Transactional
    public void saveEvent(EventEntity eventEntity) {
        IEventRepository.save(eventEntity);
    }

    @Override
    public void updateEvent() {

    }

    @Override
    public void deleteEvent(String eventId) {
        IEventRepository.deleteById(eventId);
    }
}
