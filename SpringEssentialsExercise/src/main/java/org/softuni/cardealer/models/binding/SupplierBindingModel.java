package org.softuni.cardealer.models.binding;

public class SupplierBindingModel {

    private String name;
    private Boolean isImporter;

    public SupplierBindingModel() {
    }

    public SupplierBindingModel(String name, Boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return this.isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
