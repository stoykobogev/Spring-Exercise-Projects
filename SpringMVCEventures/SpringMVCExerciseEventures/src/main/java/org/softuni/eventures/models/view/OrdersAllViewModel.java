package org.softuni.eventures.models.view;

public class OrdersAllViewModel {

    private String eventName;
    private String customerName;
    private String orderedOn;

    public OrdersAllViewModel() {
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderedOn() {
        return this.orderedOn;
    }

    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }
}
