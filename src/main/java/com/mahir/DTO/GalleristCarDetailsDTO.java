package com.mahir.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GalleristCarDetailsDTO {
    private String galleristFirstName;
    private String galleristLastName;

    private int carId;
    private String plaka;
    private String brand;
    private String model;
    private int productionYear;
    private BigDecimal price;
    private BigDecimal damagePrice;
    private String currencyCode;
    private String statusCode;
    private String imageUrl;
    private LocalDateTime createDate;

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getGalleristFirstName() { return galleristFirstName; }
    public void setGalleristFirstName(String galleristFirstName) { this.galleristFirstName = galleristFirstName; }

    public String getGalleristLastName() { return galleristLastName; }
    public void setGalleristLastName(String galleristLastName) { this.galleristLastName = galleristLastName; }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public String getPlaka() { return plaka; }
    public void setPlaka(String plaka) { this.plaka = plaka; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getProductionYear() { return productionYear; }
    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getDamagePrice() { return damagePrice; }
    public void setDamagePrice(BigDecimal damagePrice) { this.damagePrice = damagePrice; }

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }

    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}