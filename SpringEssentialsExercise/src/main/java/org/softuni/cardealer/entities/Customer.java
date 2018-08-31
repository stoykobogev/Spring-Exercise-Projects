package org.softuni.cardealer.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver", nullable = false)
    private Boolean isYoungDriver;

    public Customer() {
    }

    public Customer(String name, LocalDateTime birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.initIsYoungDriver();
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

    public LocalDateTime getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setIsYoungDriver(Boolean isYoungDriver) {
        this.isYoungDriver = isYoungDriver;
    }

    public Boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public String getBirthDateAsString() {
        return this.birthDate.toString().split("T")[0];
    }

    public void initIsYoungDriver() {
        LocalDateTime twentyYearsAgoDate =  LocalDateTime.now().minusYears(20);

        if (this.birthDate.compareTo(twentyYearsAgoDate) < 0) {
            this.isYoungDriver = false;
        } else {
            this.isYoungDriver = true;
        }
    }
}
