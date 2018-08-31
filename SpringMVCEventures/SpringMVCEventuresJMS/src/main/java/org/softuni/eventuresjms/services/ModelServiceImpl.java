package org.softuni.eventuresjms.services;

import org.softuni.eventuresjms.entities.Event;
import org.softuni.eventuresjms.entities.Order;
import org.softuni.eventuresjms.entities.User;
import org.softuni.eventuresjms.models.binding.OrderMessageBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDateTime;

import static org.softuni.eventuresjms.consts.DateTimeFormat.DATE_TIME_FORMATTER;

@Service
public class ModelServiceImpl implements ModelService {

    private final EventService eventService;
    private final UserService userService;
    private final MessageConverter messageConverter;

    @Autowired
    public ModelServiceImpl(EventService eventService, UserService userService, MessageConverter messageConverter) {
        this.eventService = eventService;
        this.userService = userService;
        this.messageConverter = messageConverter;
    }

    @Override
    public Order mapModelToOrder(OrderMessageBindingModel model) {
        Order order = new Order();
        order.setTicketsCount(model.getTicketsCount());
        order.setOrderedOn(LocalDateTime.parse(model.getOrderedOn(), DATE_TIME_FORMATTER));
        User user = this.userService.findUserByUsername(model.getCustomerName());
        order.setCustomer(user);
        Event event = this.eventService.findEventById(model.getEventId());
        order.setEvent(event);

        return order;
    }
}
