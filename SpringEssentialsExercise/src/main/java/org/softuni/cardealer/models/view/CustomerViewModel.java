package org.softuni.cardealer.models.view;

import java.math.BigDecimal;

public class CustomerViewModel {

    private String name;
    private int boughtCars;
    private BigDecimal moneySpent;

    public CustomerViewModel() {
    }

    public CustomerViewModel(String name, int boughtCars, BigDecimal moneySpent) {
        this.name = name;
        this.boughtCars = boughtCars;
        this.moneySpent = moneySpent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoughtCars() {
        return this.boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getMoneySpent() {
        return this.moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }
}
