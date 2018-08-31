package org.softuni.eventuresjms.services;

import org.softuni.eventuresjms.entities.Event;
import org.softuni.eventuresjms.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event findEventById(String id) {
        return this.eventRepository.findEventById(id);
    }
}
