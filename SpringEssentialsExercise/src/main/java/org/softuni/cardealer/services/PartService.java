package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Part;

import java.util.List;
import java.util.Map;

public interface PartService {
    void persistPart(String name, String price, String quantity, String supplierName);

    List<Part> getAll();

    String validateParams(String name, String supplierName);

    void updatePart(String oldName, String newName, String price, String supplierName);

    void deletePart(String name, String supplierName);

    Map<String, List<String>> getAllAsMapBySupplierAsKey();

    List<String> getAllNames();
}
