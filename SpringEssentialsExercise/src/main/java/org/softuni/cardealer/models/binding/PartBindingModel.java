package org.softuni.cardealer.models.binding;

import java.math.BigDecimal;

public class PartBindingModel {

    private String name;
    private BigDecimal price;
    private Long quantity;
    private String supplierName;

    public PartBindingModel() {
    }

    public PartBindingModel(String name, BigDecimal price, Long quantity, String supplierName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
