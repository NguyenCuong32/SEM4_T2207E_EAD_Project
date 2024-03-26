package org.example.brofee.services;

import org.example.brofee.dto.EventDto;
import org.example.brofee.entities.Event;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IEventService {
    List<Event> getAllEvent();
    Event getEventById(UUID id);
    Event createEvent(EventDto event, MultipartFile bannerUpload) throws IOException;
    Event updateEvent(UUID id, EventDto event, MultipartFile bannerUpload) throws IOException;
    void deleteEvent(UUID id);
}
