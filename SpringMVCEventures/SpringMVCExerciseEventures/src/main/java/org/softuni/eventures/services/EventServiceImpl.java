package org.softuni.eventures.services;

import org.softuni.eventures.entities.Event;
import org.softuni.eventures.entities.User;
import org.softuni.eventures.models.view.EventsMineViewModel;
import org.softuni.eventures.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserService userService;
    private final ModelService modelService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserService userService, ModelService modelService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.modelService = modelService;
    }

    @Override
    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public void saveEvent(Event event) {
        this.eventRepository.saveAndFlush(event);
    }

    @Override
    public List<EventsMineViewModel> getEventsMineViewModelListForUser(String username) {
        User user = this.userService.getUserByUsername(username);
        return this.modelService.mapOrderListToEventsMineViewModelList(user.getOrders());
    }

    @Override
    public Page<Event> getPage(Pageable pageable) {
        return this.eventRepository.findAll(pageable);
    }
}
