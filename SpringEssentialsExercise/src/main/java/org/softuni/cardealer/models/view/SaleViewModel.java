package org.softuni.cardealer.models.view;

public class SaleViewModel {

    private Long id;
    private int discount;
    private String priceFull;
    private String priceDiscounted;
    private String carMake;
    private String carModel;
    private String customerName;
    private String carTravelledDistance;

    public SaleViewModel() {
    }

    public SaleViewModel(Long id, int discount, String priceFull, String priceDiscounted) {
        this.id = id;
        this.discount = discount;
        this.priceFull = priceFull;
        this.priceDiscounted = priceDiscounted;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPriceFull() {
        return this.priceFull;
    }

    public void setPriceFull(String priceFull) {
        this.priceFull = priceFull;
    }

    public String getPriceDiscounted() {
        return this.priceDiscounted;
    }

    public void setPriceDiscounted(String priceDiscounted) {
        this.priceDiscounted = priceDiscounted;
    }

    public String getCarMake() {
        return this.carMake;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarTravelledDistance() {
        return this.carTravelledDistance;
    }

    public void setCarTravelledDistance(String carTravelledDistance) {
        this.carTravelledDistance = carTravelledDistance;
    }
}
