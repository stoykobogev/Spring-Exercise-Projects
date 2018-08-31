package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query("select count(p) from Part as p " +
            "where p.supplier = ?1")
    int getPartsCountBySupplier(Supplier supplier);

    @Query("select p.name from Part as p")
    List<String> getAllNames();

    Part findFirstByName(String name);

    Part findFirstByNameAndSupplierName(String name, String supplierName);

    @Query("select p from Part as p " +
            "where p.name in ?1")
    List<Part> findPartsByListOfNames(List<String> partNameList);

    List<Part> findAllBySupplierId(Long id);
}
