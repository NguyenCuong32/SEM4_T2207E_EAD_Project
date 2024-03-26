package org.example.brofee.repositories;

import org.example.brofee.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEventRepository extends JpaRepository<Event, UUID> {
    public Event findEventsByName(String name);

}
