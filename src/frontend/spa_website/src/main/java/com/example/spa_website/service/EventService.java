package com.example.spa_website.service;

import com.example.spa_website.dto.EventDTO;
import com.example.spa_website.dto.EventDetailDTO;
import com.example.spa_website.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<EventDTO> getEvents(int status, int limit) {
        return eventRepository.findTopByStatusOrderByIdDesc(status, limit)
                .stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .toList();
    }

    public EventDetailDTO getEventDetail(Long id) {
        return modelMapper.map(eventRepository.findById(id).orElse(null), EventDetailDTO.class);
    }
}
