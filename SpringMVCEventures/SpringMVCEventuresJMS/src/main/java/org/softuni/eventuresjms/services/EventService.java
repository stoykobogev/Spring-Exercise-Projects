package org.softuni.eventuresjms.services;

import org.softuni.eventuresjms.entities.Event;

public interface EventService {

    Event findEventById(String id);
}
