package org.softuni.eventures.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "event_id")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime start;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime end;

    @Column(nullable = false)
    private Long totalTickets;

    @Column(nullable = false, scale = 2)
    private BigDecimal pricePerTicket;

    public Event() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getStart() {
        return this.start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Long getTotalTickets() {
        return this.totalTickets;
    }

    public void setTotalTickets(Long totalTickets) {
        this.totalTickets = totalTickets;
    }

    public BigDecimal getPricePerTicket() {
        return this.pricePerTicket;
    }

    public void setPricePerTicket(BigDecimal pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
    }
}
