package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByCustomerEquals(Customer customer);

    List<Sale> findAllBy();

    @Query("SELECT s FROM Sale AS s " +
            "WHERE s.discount > 0.00")
    List<Sale> findAllDiscounted();

    List<Sale> findAllByDiscount(BigDecimal percentage);

    Sale findFirstById(Long id);
}
