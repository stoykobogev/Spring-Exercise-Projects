package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.repositories.CarRepository;
import org.softuni.cardealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_EXISTS = "Car with this make and model already exists";

    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public List<Car> getCarsByMakeSorted(String make) {
        return this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);
    }

    @Override
    public Car getCarById(Long id) {
        return this.carRepository.findFirstById(id);
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAllBy();
    }

    @Override
    public Car persistCar(String make, String model, String travelledDistance, String parts) {
        List<String> partNameList = Arrays.stream(parts.split(", "))
                .filter(x -> !x.isEmpty()).collect(Collectors.toList());

        List<Part> partList = this.partRepository.findPartsByListOfNames(partNameList);

        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setParts(partList);
        car.setTravelledDistance(Long.parseLong(travelledDistance));

        return this.carRepository.saveAndFlush(car);
    }

    @Override
    public String validateCar(String make, String model) {
        Car car = this.carRepository.findFirstByMakeAndModel(make, model);

        if (car != null) {
            return CAR_EXISTS;
        }

        return null;
    }
}
