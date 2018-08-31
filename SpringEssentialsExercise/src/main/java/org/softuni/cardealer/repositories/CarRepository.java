package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    Car findFirstById(Long id);

    List<Car> findAllBy();

    Car findFirstByMakeAndModel(String make, String model);
}
