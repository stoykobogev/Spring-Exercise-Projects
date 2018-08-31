package org.softuni.eventures.services;

import org.softuni.eventures.entities.Event;
import org.softuni.eventures.entities.Order;
import org.softuni.eventures.entities.User;
import org.softuni.eventures.models.binding.CreateEventBindingModel;
import org.softuni.eventures.models.binding.UserRegisterBindingModel;
import org.softuni.eventures.models.view.EventsAllViewModel;
import org.softuni.eventures.models.view.EventsMineViewModel;
import org.softuni.eventures.models.view.OrdersAllViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModelService {
    User mapModelToUser(UserRegisterBindingModel model);

    Event mapModelToEvent(CreateEventBindingModel model);

    EventsAllViewModel mapEventToEventsAllViewModel(Event event);

    List<EventsAllViewModel> mapEventListToEventsAllViewModelList(List<Event> eventList);

    Page<EventsAllViewModel> mapEventPageToEventsAllViewModelPage(Page<Event> eventPage);

    List<OrdersAllViewModel> mapOrderListToModelList(List<Order> orderList);

    OrdersAllViewModel mapOrderToModel(Order order);

    EventsMineViewModel mapOrderToEventsMineViewModel(Order order);

    List<EventsMineViewModel> mapOrderListToEventsMineViewModelList(List<Order> orderList);
}
