package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.services.CarService;
import org.softuni.cardealer.services.PartService;
import org.softuni.cardealer.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final PartService partService;
    private final Logger logger;

    @Autowired
    public CarController(CarService carService, PartService partService, Logger logger) {
        this.carService = carService;
        this.partService = partService;
        this.logger = logger;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<Car> carList = this.carService.getAllCars();

        modelAndView.addObject("cars", carList);
        modelAndView.setViewName("cars/make");

        return modelAndView;
    }

    @GetMapping("/{make}")
    public ModelAndView make(@PathVariable String make, ModelAndView modelAndView) {
        List<Car> carList = this.carService.getCarsByMakeSorted(make);

        modelAndView.addObject("cars", carList);
        modelAndView.setViewName("cars/make");

        return modelAndView;
    }

    @GetMapping("/{id}/parts")
    public ModelAndView carParts(@PathVariable Long id, ModelAndView modelAndView) {
        Car car = this.carService.getCarById(id);

        modelAndView.addObject("car", car);
        modelAndView.setViewName("cars/parts");

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addGet(HttpSession session, ModelAndView modelAndView) {

        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
        } else {
            Map<String, List<String>> supplierPartsMap = this.partService.getAllAsMapBySupplierAsKey();
            modelAndView.addObject("supplierPartsMap", supplierPartsMap);
            modelAndView.setViewName("cars/add");
        }

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@RequestParam String make,
                                @RequestParam String model,
                                @RequestParam String travelledDistance,
                                @RequestParam String parts,
                                HttpSession session,
                                ModelAndView modelAndView) {

        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
        } else {
            String exceptionMessage = this.carService.validateCar(make, model);

            if (exceptionMessage != null) {
                modelAndView.addObject("exceptionMessage", exceptionMessage);
                modelAndView.setViewName("cars/add");
            } else {
                Car car = this.carService.persistCar(make, model, travelledDistance, parts);
                this.logger.logEvent(session, "Add", "Car");
                modelAndView.addObject("car", car);
                modelAndView.setViewName("redirect:/cars/" + car.getId() + "/parts");
            }
        }

        return modelAndView;
    }
}
