package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByIsImporterEquals(boolean isImporter);

    @Query("select s.name from Supplier as s")
    List<String> findAllSupplierNames();

    Supplier findFirstByName(String name);

    Supplier findFirstById(Long id);
}
