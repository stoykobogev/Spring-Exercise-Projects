package org.softuni.cardealer.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
public class Sale {

    private final static BigDecimal YOUNG_CUSTOMER_DISCOUNT = new BigDecimal(0.05);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount")
    private BigDecimal discount;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    public Sale() {
    }

    public Sale(BigDecimal discount, Car car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
        this.checkAndSetCustomerForYoungDriverDiscont();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private void checkAndSetCustomerForYoungDriverDiscont() {
        if (this.customer.isYoungDriver()) {
            this.discount = this.discount.add(YOUNG_CUSTOMER_DISCOUNT);
        }
    }
}
