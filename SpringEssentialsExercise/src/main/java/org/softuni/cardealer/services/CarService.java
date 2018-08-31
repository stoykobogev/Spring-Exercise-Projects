package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Car;

import java.util.List;

public interface CarService {
    List<Car> getCarsByMakeSorted(String make);

    Car getCarById(Long id);

    List<Car> getAllCars();

    Car persistCar(String make, String model, String travelledDistance, String parts);

    String validateCar(String make, String model);
}
