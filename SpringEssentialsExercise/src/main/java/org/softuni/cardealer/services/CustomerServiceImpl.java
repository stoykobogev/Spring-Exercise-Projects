package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.entities.Sale;
import org.softuni.cardealer.models.view.CustomerViewModel;
import org.softuni.cardealer.repositories.CustomerRepository;
import org.softuni.cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String EMPTY_NAME_EXCEPTION_MESSAGE = "Name cannot be empty";
    private static final String NAME_EXISTS_EXCEPTION_MESSAGE = "Customer with that name already exists";
    private static final String EMPTY_DATE_EXCEPTION_MESSAGE = "Birth date cannot be empty";

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Customer> getAllCustomersAscending() {
        return this.customerRepository.findAllOrderedByBirthDateAscending().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Customer> getAllCustomersDescending() {
        return this.customerRepository.findAllOrderedByBirthDateDescending().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CustomerViewModel getCustomerViewModel(Long id) {
        Customer customer = this.customerRepository.findFirstById(id);
        List<Sale> customerSales = this.saleRepository.findAllByCustomerEquals(customer);

        CustomerViewModel customerViewModel = new CustomerViewModel();
        customerViewModel.setName(customer.getName());
        customerViewModel.setBoughtCars(customerSales.size());
        BigDecimal totalMoneySpent = new BigDecimal(0);

        for (Sale sale : customerSales) {
            BigDecimal carPrice = sale.getCar().getPrice();

            totalMoneySpent = totalMoneySpent.add(carPrice.multiply(BigDecimal.ONE.subtract(sale.getDiscount())));
        }

        totalMoneySpent = totalMoneySpent.setScale(2, RoundingMode.HALF_UP);

        customerViewModel.setMoneySpent(totalMoneySpent);

        return customerViewModel;
    }

    @Override
    public String validateParams(String name, String birthDate) {

        if (name.isEmpty()) {
            return EMPTY_NAME_EXCEPTION_MESSAGE;
        }

        if (birthDate.isEmpty()) {
            return EMPTY_DATE_EXCEPTION_MESSAGE;
        }

        Customer customer = this.customerRepository.findFirstByName(name);
        if (customer != null) {
            return NAME_EXISTS_EXCEPTION_MESSAGE;
        }

        return null;
    }

    @Override
    public String validateParams(String oldName, String newName, String birthDate) {

        if (newName.isEmpty()) {
            return EMPTY_NAME_EXCEPTION_MESSAGE;
        }

        if (birthDate.isEmpty()) {
            return EMPTY_DATE_EXCEPTION_MESSAGE;
        }

        if (!oldName.equals(newName)) {
            Customer customer = this.customerRepository.findFirstByName(newName);
            if (customer != null) {
                return NAME_EXISTS_EXCEPTION_MESSAGE;
            }
        }

        return null;
    }

    @Override
    public void persistCustomer(String name, String birthDate) {
        LocalDateTime birthDateParsed = parseDate(birthDate);

        Customer customer = new Customer(name, birthDateParsed);

        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCustomer(String oldName, String newName, String birthDate) {
        LocalDateTime birthDateParsed = parseDate(birthDate);

        Customer customer = this.customerRepository.findFirstByName(oldName);
        customer.setName(newName);
        customer.setBirthDate(birthDateParsed);
        customer.initIsYoungDriver();

        this.customerRepository.saveAndFlush(customer);
    }

    private LocalDateTime parseDate(String date) {
        String[] dateSplit = date.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        LocalDateTime dateParsed = LocalDateTime.of(year, month, day, 0, 0);

        return dateParsed;
    }
}
