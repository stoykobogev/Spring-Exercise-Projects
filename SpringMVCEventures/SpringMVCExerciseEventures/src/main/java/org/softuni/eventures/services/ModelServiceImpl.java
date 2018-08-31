package org.softuni.eventures.services;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.softuni.eventures.entities.Event;
import org.softuni.eventures.entities.Order;
import org.softuni.eventures.entities.User;
import org.softuni.eventures.models.binding.CreateEventBindingModel;
import org.softuni.eventures.models.binding.UserRegisterBindingModel;
import org.softuni.eventures.models.view.EventsAllViewModel;
import org.softuni.eventures.models.view.EventsMineViewModel;
import org.softuni.eventures.models.view.OrdersAllViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.softuni.eventures.consts.DateTimeFormat.DATE_TIME_FORMATTER;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configMapper();
    }

    @Override
    public User mapModelToUser(UserRegisterBindingModel model) {
        return this.modelMapper.map(model, User.class);
    }

    @Override
    public Event mapModelToEvent(CreateEventBindingModel model) {
        return this.modelMapper.map(model, Event.class);
    }

    @Override
    public EventsAllViewModel mapEventToEventsAllViewModel(Event event) {
        return this.modelMapper.map(event, EventsAllViewModel.class);
    }

    @Override
    public List<EventsAllViewModel> mapEventListToEventsAllViewModelList(List<Event> eventList) {
        return eventList.stream()
                .map(this::mapEventToEventsAllViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EventsAllViewModel> mapEventPageToEventsAllViewModelPage(Page<Event> eventPage) {
        return eventPage.map(this::mapEventToEventsAllViewModel);

    }

    @Override
    public List<OrdersAllViewModel> mapOrderListToModelList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapOrderToModel)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersAllViewModel mapOrderToModel(Order order) {
        OrdersAllViewModel model = new OrdersAllViewModel();
        model.setOrderedOn(order.getOrderedOn().format(DATE_TIME_FORMATTER));
        model.setEventName(order.getEvent().getName());
        model.setCustomerName(order.getCustomer().getUsername());

        return model;
    }

    @Override
    public EventsMineViewModel mapOrderToEventsMineViewModel(Order order) {
        EventsMineViewModel model = new EventsMineViewModel();
        model.setName(order.getEvent().getName());
        model.setStart(order.getEvent().getStart().format(DATE_TIME_FORMATTER));
        model.setEnd(order.getEvent().getEnd().format(DATE_TIME_FORMATTER));
        model.setTicketsCount(order.getTicketsCount());

        return model;
    }

    @Override
    public List<EventsMineViewModel> mapOrderListToEventsMineViewModelList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapOrderToEventsMineViewModel)
                .collect(Collectors.toList());
    }

    private void configMapper() {

        //Don't change to lambda function, it breaks

        this.modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
                return  LocalDateTime.parse(context.getSource(), DATE_TIME_FORMATTER);
            }
        });

        this.modelMapper.addConverter(new Converter<LocalDateTime, String>() {
            @Override
            public String convert(MappingContext<LocalDateTime, String> context) {
                return  context.getSource().format(DATE_TIME_FORMATTER);
            }
        });
        this.modelMapper.addConverter(new Converter<String, BigDecimal>() {
            @Override
            public BigDecimal convert(MappingContext<String, BigDecimal> context) {
                return  new BigDecimal(context.getSource());
            }
        });
    }
}
