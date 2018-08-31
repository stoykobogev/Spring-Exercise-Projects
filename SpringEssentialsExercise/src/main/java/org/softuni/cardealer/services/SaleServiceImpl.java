package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Car;
import org.softuni.cardealer.entities.Customer;
import org.softuni.cardealer.entities.Sale;
import org.softuni.cardealer.models.view.SaleViewModel;
import org.softuni.cardealer.repositories.CarRepository;
import org.softuni.cardealer.repositories.CustomerRepository;
import org.softuni.cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private static final String CUSTOMER_NOT_EXIST = "Customer with this name does not exist";
    private static final String CAR_NOT_EXIST = "Car with this model and make does not exist";

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<Sale> getAllSales() {
        return this.saleRepository.findAllBy();
    }

    @Override
    public SaleViewModel getSaleViewModelById(Long id) {
        return convertSaleToViewModel(this.saleRepository.findFirstById(id));
    }

    @Override
    public SaleViewModel createSaleViewModel(
            String customerName, String carMake, String carModel, String discountPercentage) {
        Customer customer = this.customerRepository.findFirstByName(customerName);
        Car car = this.carRepository.findFirstByMakeAndModel(carMake, carModel);
        BigDecimal discount = new BigDecimal(Integer.parseInt(discountPercentage) / 100.0);

        Sale sale = new Sale(discount, car, customer);
        return convertSaleToViewModel(sale);
    }

    @Override
    public List<SaleViewModel> getSaleViewModelWithDiscount() {
        List<Sale> saleList = this.saleRepository.findAllDiscounted();
        List<SaleViewModel> saleViewModelList = convertSalesListToViewModelList(saleList);

        return saleViewModelList;
    }

    @Override
    public List<SaleViewModel> getSaleViewModelWithDiscount(int percentage) {
        BigDecimal percentageDecimal = new BigDecimal(percentage/100.0);
        List<Sale> saleList = this.saleRepository.findAllByDiscount(percentageDecimal);
        List<SaleViewModel> saleViewModelList = convertSalesListToViewModelList(saleList);

        return saleViewModelList;
    }

    @Override
    public String validateSale(String customerName, String carMake, String carModel) {
        Customer customer = this.customerRepository.findFirstByName(customerName);
        if (customer == null) {
            return CUSTOMER_NOT_EXIST;
        }

        Car car = this.carRepository.findFirstByMakeAndModel(carMake, carModel);
        if (car == null) {
            return CAR_NOT_EXIST;
        }

        return null;
    }

    @Override
    public Sale persistSale(String customerName, String carMake, String carModel, String discountPercentage) {

        Customer customer = this.customerRepository.findFirstByName(customerName);
        Car car = this.carRepository.findFirstByMakeAndModel(carMake, carModel);
        BigDecimal discount = new BigDecimal(Integer.parseInt(discountPercentage) / 100.0);

        Sale sale = new Sale(discount, car, customer);

        return this.saleRepository.saveAndFlush(sale);
    }

    @Override
    public Sale persistSale(SaleViewModel saleViewModel) {

        Customer customer = this.customerRepository.findFirstByName(saleViewModel.getCustomerName());
        Car car = this.carRepository.findFirstByMakeAndModel(saleViewModel.getCarMake(), saleViewModel.getCarModel());
        BigDecimal discount = new BigDecimal(saleViewModel.getDiscount() / 100.0);

        Sale sale = new Sale(discount, car, customer);

        return this.saleRepository.saveAndFlush(sale);
    }

    private List<SaleViewModel> convertSalesListToViewModelList(List<Sale> saleList) {
        return saleList.stream()
                .map(sale -> {
                    return convertSaleToViewModel(sale);
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private SaleViewModel convertSaleToViewModel(Sale sale) {
        SaleViewModel saleViewModel = new SaleViewModel();
        saleViewModel.setId(sale.getId());
        BigDecimal price = sale.getCar().getPrice();
        saleViewModel.setDiscount((int)(sale.getDiscount().doubleValue() * 100));
        saleViewModel.setPriceFull(price.toString());
        saleViewModel.setPriceDiscounted(price.multiply(BigDecimal.ONE.subtract(sale.getDiscount()))
                .setScale(2, RoundingMode.HALF_UP).toString());
        saleViewModel.setCarMake(sale.getCar().getMake());
        saleViewModel.setCarModel(sale.getCar().getModel());
        saleViewModel.setCustomerName(sale.getCustomer().getName());
        saleViewModel.setCarTravelledDistance(sale.getCar().getTravelledDistance().toString());
        return saleViewModel;
    }
}
