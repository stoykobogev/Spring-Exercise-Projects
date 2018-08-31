package org.softuni.cardealer.models.binding;

import java.time.LocalDateTime;

public class CustomerBindingModel {

    private String name;
    private LocalDateTime birthDate;
    private Boolean isYoungDriver;

    public CustomerBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
