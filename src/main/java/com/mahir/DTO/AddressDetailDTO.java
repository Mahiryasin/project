package com.mahir.DTO;

public class AddressDetailDTO {

    private String city;
    private String district;
    private String neighborhood;
    private String address;

    public AddressDetailDTO() {
    }

    public AddressDetailDTO(String city, String district, String neighborhood, String address) {
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
        this.address = address;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
