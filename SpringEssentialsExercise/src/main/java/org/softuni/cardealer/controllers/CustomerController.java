package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.models.view.CustomerViewModel;
import org.softuni.cardealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all/ascending")
    public ModelAndView allAscending(ModelAndView modelAndView) {
        List<Customer> customerList = this.customerService.getAllCustomersAscending();

        modelAndView.setViewName("customers/all");
        modelAndView.addObject("customers", customerList);

        return modelAndView;
    }

    @GetMapping("/all/descending")
    public ModelAndView allDescending(ModelAndView modelAndView) {
        List<Customer> customerList = this.customerService.getAllCustomersDescending();

        modelAndView.setViewName("customers/all");
        modelAndView.addObject("customers", customerList);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView customerAndSales(@PathVariable Long id, ModelAndView modelAndView) {
        CustomerViewModel customerViewModel = this.customerService.getCustomerViewModel(id);
        modelAndView.addObject("customer", customerViewModel);
        modelAndView.setViewName("customers/details");

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addGet(ModelAndView modelAndView) {
        modelAndView.setViewName("customers/add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@RequestParam String name,
                                @RequestParam String birthDate,
                                ModelAndView modelAndView) {

        String exceptionMessage = this.customerService.validateParams(name, birthDate);

        if (exceptionMessage != null) {
            modelAndView.addObject("exceptionMessage", exceptionMessage);
            modelAndView.setViewName("customers/add");
        } else {
            this.customerService.persistCustomer(name, birthDate);
            modelAndView.setViewName("redirect:/customers/all/ascending");
        }

        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editGet(@RequestParam String name, @RequestParam String birthDate, ModelAndView modelAndView) {
        modelAndView.addObject("name", name);
        modelAndView.addObject("birthDate", birthDate);
        modelAndView.setViewName("customers/edit");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editPost(@RequestParam String newName,
                                 @RequestParam String oldName,
                                 @RequestParam String birthDate,
                                 ModelAndView modelAndView) {

        String exceptionMessage = this.customerService.validateParams(oldName, newName, birthDate);
        if (exceptionMessage != null) {
            modelAndView.addObject("exceptionMessage", exceptionMessage);
            modelAndView.addObject("name", oldName);
            modelAndView.addObject("birthDate", birthDate);
            modelAndView.setViewName("customers/edit");
        } else {
            this.customerService.updateCustomer(oldName, newName, birthDate);
            modelAndView.setViewName("redirect:/customers/all/ascending");
        }

        return modelAndView;
    }
}
