package org.softuni.eventuresjms.services;

import org.softuni.eventuresjms.entities.Order;

public interface OrderService {

    void saveOrder(Order order);
}
