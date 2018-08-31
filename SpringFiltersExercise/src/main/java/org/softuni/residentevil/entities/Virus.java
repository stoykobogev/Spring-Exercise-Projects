package org.softuni.residentevil.entities;

import org.softuni.residentevil.common.enums.Magnitude;
import org.softuni.residentevil.common.enums.Mutation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String sideEffects;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private Boolean isDeadly;

    @Column(nullable = false)
    private Boolean isCurable;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Mutation mutation;

    @Column(nullable = false)
    private Integer turnoverRate;

    @Column(nullable = false)
    private Integer hoursUntilTurn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Magnitude magnitude;

    @Column(nullable = false)
    private LocalDate releasedOn;

    @ManyToMany
    private List<Capital> capitals;

    public Virus() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsDeadly() {
        return this.isDeadly;
    }

    public void setIsDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getIsCurable() {
        return this.isCurable;
    }

    public void setIsCurable(Boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
