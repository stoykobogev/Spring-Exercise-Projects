package org.softuni.eventuresjms.listeners;

import org.softuni.eventuresjms.entities.Order;
import org.softuni.eventuresjms.models.binding.OrderMessageBindingModel;
import org.softuni.eventuresjms.services.ModelService;
import org.softuni.eventuresjms.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class OrderListener {

    private final ModelService modelService;
    private final OrderService orderService;

    @Autowired
    public OrderListener(ModelService modelService, OrderService orderService) {
        this.modelService = modelService;
        this.orderService = orderService;
    }

    @JmsListener(destination = "order", containerFactory = "myFactory")
    public void receiveMessage(OrderMessageBindingModel orderModel) {
       Order order = this.modelService.mapModelToOrder(orderModel);
       this.orderService.saveOrder(order);
    }
}
