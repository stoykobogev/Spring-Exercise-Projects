package org.softuni.cardealer.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_importer", nullable = false)
    private Boolean isImporter;

    public Supplier() {
    }

    public Supplier(String name, Boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
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

    public Boolean getIsImporter() {
        return this.isImporter;
    }

    public void setIsImporter(Boolean importer) {
        isImporter = importer;
    }

    public Boolean isImporter() {
        return this.isImporter;
    }
}
