package org.softuni.residentevil.repositories;

import org.softuni.residentevil.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long> {

    List<Capital> findCapitalsByNameIn(List<String> capitals);
}
