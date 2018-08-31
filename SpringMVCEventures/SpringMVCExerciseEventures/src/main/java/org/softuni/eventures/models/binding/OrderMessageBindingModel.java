package org.softuni.eventures.models.binding;

import java.io.Serializable;

public class OrderMessageBindingModel {

    private String orderedOn;
    private int ticketsCount;
    private String eventId;
    private String customerName;

    public OrderMessageBindingModel() {
    }

    public String getOrderedOn() {
        return this.orderedOn;
    }

    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }

    public int getTicketsCount() {
        return this.ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
