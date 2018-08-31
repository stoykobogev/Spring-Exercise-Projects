package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer AS c " +
            "ORDER BY c.birthDate ASC, c.isYoungDriver ASC")
    List<Customer> findAllOrderedByBirthDateAscending();

    @Query("SELECT c FROM Customer AS c " +
            "ORDER BY c.birthDate DESC, c.isYoungDriver ASC")
    List<Customer> findAllOrderedByBirthDateDescending();

    Customer findFirstById(Long id);

    Customer findFirstByName(String name);
}
