package org.softuni.eventures.services;

import org.softuni.eventures.models.binding.OrderMessageBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.softuni.eventures.consts.DateTimeFormat.DATE_TIME_FORMATTER;

@Service
public class MessageServiceImpl implements MessageService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendMessage(OrderMessageBindingModel orderModel) {
        orderModel.setOrderedOn(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        this.jmsTemplate.convertAndSend("order", orderModel);
    }
}
