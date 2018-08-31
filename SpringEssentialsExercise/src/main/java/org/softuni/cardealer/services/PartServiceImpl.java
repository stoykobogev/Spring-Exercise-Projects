package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.entities.Supplier;
import org.softuni.cardealer.repositories.PartRepository;
import org.softuni.cardealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PartServiceImpl implements PartService {

    private static final String EMPTY_NAME_EXCEPTION_MESSAGE = "Name cannot be empty";
    private static final String NAME_EXISTS_EXCEPTION_MESSAGE = "Part with that name and supplier already exists";

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void persistPart(String name, String price, String quantity, String supplierName) {
        Part part = new Part();
        part.setName(name);
        part.setPrice(new BigDecimal(price));
        part.setQuantity(Long.parseLong(quantity));

        Supplier supplier = this.supplierRepository.findFirstByName(supplierName);
        part.setSupplier(supplier);

        this.partRepository.saveAndFlush(part);
    }

    @Override
    public List<Part> getAll() {
        return this.partRepository.findAll();
    }

    @Override
    public String validateParams(String name, String supplierName) {
        if (name.isEmpty()) {
            return EMPTY_NAME_EXCEPTION_MESSAGE;
        }

        Part part = this.partRepository.findFirstByNameAndSupplierName(name, supplierName);
        if (part != null) {
            return NAME_EXISTS_EXCEPTION_MESSAGE;
        }

        return null;
    }

    @Override
    public void updatePart(String name, String price, String quantity, String supplierName) {
        Part part = this.partRepository.findFirstByNameAndSupplierName(name, supplierName);

        part.setQuantity(Long.parseLong(quantity));
        part.setPrice(new BigDecimal(price));

        this.partRepository.saveAndFlush(part);
    }

    @Override
    public void deletePart(String name, String supplierName) {
        Part part = this.partRepository.findFirstByNameAndSupplierName(name, supplierName);
        this.partRepository.delete(part);
    }

    @Override
    public Map<String, List<String>> getAllAsMapBySupplierAsKey() {
        List<Part> partList = this.partRepository.findAll();
        List<String> supplierNames = this.supplierRepository.findAllSupplierNames();
        Map<String, List<String>> supplierPartsMap = new HashMap<>();

        for (String supplierName : supplierNames) {
            supplierPartsMap.put(supplierName, new ArrayList<>());
            for (Part part : partList) {
                if (supplierName.equals(part.getSupplier().getName())) {
                    supplierPartsMap.get(supplierName).add(part.getName());
                }
            }
        }

        return supplierPartsMap;
    }

    @Override
    public List<String> getAllNames() {
        return this.partRepository.getAllNames();
    }
}
