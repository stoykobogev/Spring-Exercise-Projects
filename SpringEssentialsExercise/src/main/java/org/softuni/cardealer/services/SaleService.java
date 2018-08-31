package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Sale;
import org.softuni.cardealer.models.view.SaleViewModel;

import java.util.List;

public interface SaleService {
    List<Sale> getAllSales();

    SaleViewModel getSaleViewModelById(Long id);

    SaleViewModel createSaleViewModel(
            String customerName, String carMake, String carModel, String discountPercentage);

    List<SaleViewModel> getSaleViewModelWithDiscount();

    List<SaleViewModel> getSaleViewModelWithDiscount(int percentage);

    String validateSale(String customerName, String carMake, String carModel);

    Sale persistSale(
            String customerName, String carMake, String carModel, String discountPercentage);

    Sale persistSale(SaleViewModel saleViewModel);
}
