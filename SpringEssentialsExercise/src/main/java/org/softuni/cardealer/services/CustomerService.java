package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.models.view.CustomerViewModel;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomersAscending();

    List<Customer> getAllCustomersDescending();

    CustomerViewModel getCustomerViewModel(Long id);

    String validateParams(String name, String birthDate);

    String validateParams(String oldName, String newName, String birthDate);

    void persistCustomer(String name, String birthDate);

    void updateCustomer(String oldName, String newName, String birthDate);
}
