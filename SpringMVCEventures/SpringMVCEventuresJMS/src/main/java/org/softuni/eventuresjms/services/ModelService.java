package org.softuni.eventuresjms.services;

import org.softuni.eventuresjms.entities.Order;
import org.softuni.eventuresjms.models.binding.OrderMessageBindingModel;

import javax.jms.Message;

public interface ModelService {

    Order mapModelToOrder(OrderMessageBindingModel model);
}
