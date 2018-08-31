package org.softuni.eventures.services;

import org.softuni.eventures.models.binding.OrderMessageBindingModel;

public interface MessageService {
    void sendMessage(OrderMessageBindingModel orderModel);
}
