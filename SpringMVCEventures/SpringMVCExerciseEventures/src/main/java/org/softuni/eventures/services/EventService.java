package org.softuni.eventures.services;

import org.softuni.eventures.entities.Event;
import org.softuni.eventures.models.view.EventsMineViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    List<Event> getAll();

    void saveEvent(Event event);

    List<EventsMineViewModel> getEventsMineViewModelListForUser(String username);

    Page<Event> getPage(Pageable pageable);
}
