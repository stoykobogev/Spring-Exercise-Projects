package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Supplier;
import org.softuni.cardealer.models.view.SupplierViewModel;

import java.util.List;

public interface SupplierService {
    List<String> getAllNames();

    List<SupplierViewModel> getAllLocalSuppliersViewModel();

    List<SupplierViewModel> getAllImportingSuppliersViewModel();

    String validateSupplier(String name);

    String validateSupplier(String oldName, String newName);

    Supplier getSupplierById(Long id);

    void persistSupplier(String name, Boolean isImporter);

    void updateSupplier(Long id, String newName, Boolean isImporter);

    void deleteSupplier(Long id);
}
