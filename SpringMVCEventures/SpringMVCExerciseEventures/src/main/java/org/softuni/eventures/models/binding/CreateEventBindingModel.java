package org.softuni.eventures.models.binding;

import java.math.BigDecimal;

public class CreateEventBindingModel {

    private String name;
    private String place;
    private String start;
    private String end;
    private long totalTickets;
    private BigDecimal pricePerTicket;

    public CreateEventBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public long getTotalTickets() {
        return this.totalTickets;
    }

    public void setTotalTickets(long totalTickets) {
        this.totalTickets = totalTickets;
    }

    public BigDecimal getPricePerTicket() {
        return this.pricePerTicket;
    }

    public void setPricePerTicket(BigDecimal pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
    }
}
