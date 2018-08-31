package org.softuni.cardealer.models.view;

public class SupplierViewModel {

    private Long id;
    private String name;
    private int partsCount;

    public SupplierViewModel() {
    }

    public SupplierViewModel(Long id, String name, int partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
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

    public int getPartsCount() {
        return this.partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
