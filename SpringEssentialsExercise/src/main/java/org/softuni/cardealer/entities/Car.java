package org.softuni.cardealer.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "travelled_distance", nullable = false)
    private Long travelledDistance;

    @ManyToMany
    @JoinTable(name = "parts_cars",
        joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id", table = "cars"),
        inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id", table = "parts")
    )
    private List<Part> parts;

    public Car() {
    }

    public Car(String make, String model, Long travelledDistance, List<Part> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public BigDecimal getPrice() {
        return this.parts.stream().map(Part::getPrice).reduce(BigDecimal::add).orElse(null);
    }
}
