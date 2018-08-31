package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.entities.Supplier;
import org.softuni.cardealer.models.view.SupplierViewModel;
import org.softuni.cardealer.repositories.CarRepository;
import org.softuni.cardealer.repositories.PartRepository;
import org.softuni.cardealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static final String SUPPLIER_EXISTS_EXCEPTION_MESSAGE = "Supplier with this name already exists";

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               PartRepository partRepository, CarRepository carRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<String> getAllNames() {
        return this.supplierRepository.findAllSupplierNames();
    }

    @Override
    public List<SupplierViewModel> getAllLocalSuppliersViewModel() {
        return convertSupplierToViewModel(this.supplierRepository.findAllByIsImporterEquals(false));
    }

    @Override
    public List<SupplierViewModel> getAllImportingSuppliersViewModel() {
        return convertSupplierToViewModel(this.supplierRepository.findAllByIsImporterEquals(true));
    }

    @Override
    public String validateSupplier(String name) {
        Supplier supplier = this.supplierRepository.findFirstByName(name);

        if (supplier != null) {
            return SUPPLIER_EXISTS_EXCEPTION_MESSAGE;
        }

        return null;
    }

    @Override
    public String validateSupplier(String oldName, String newName) {
        if (!oldName.equals(newName)) {
            return this.validateSupplier(newName);
        }

        return null;
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return this.supplierRepository.findFirstById(id);
    }

    @Override
    public void persistSupplier(String name, Boolean isImporter) {
        Supplier supplier = new Supplier(name, isImporter);

        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void updateSupplier(Long id, String newName, Boolean isImporter) {
        Supplier supplier = this.supplierRepository.findFirstById(id);

        supplier.setIsImporter(isImporter);
        supplier.setName(newName);

        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        List<Part> partList = this.partRepository.findAllBySupplierId(id);
        for (Part part : partList) {
            for (Car car : part.getCars()) {
                car.getParts().remove(part);
                this.carRepository.save(car);
            }
            part.getCars().clear();
            this.partRepository.save(part);
        }
        this.carRepository.flush();
        this.partRepository.flush();
        this.partRepository.deleteAll(partList);
        this.supplierRepository.deleteById(id);
    }

    private List<SupplierViewModel> convertSupplierToViewModel(List<Supplier> suppliers) {
        return suppliers.stream()
                .map(supplier -> {
                    SupplierViewModel supplierViewModel = new SupplierViewModel();
                    supplierViewModel.setId(supplier.getId());
                    supplierViewModel.setName(supplier.getName());
                    supplierViewModel.setPartsCount(
                            this.partRepository.getPartsCountBySupplier(supplier));
                    return supplierViewModel;
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
