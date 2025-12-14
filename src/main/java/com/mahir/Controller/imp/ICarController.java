package com.mahir.Controller.imp;

import java.util.List;

import com.mahir.DTO.CarFilterRequest;
import com.mahir.DTO.CarWithOwnerDTO;
import com.mahir.DTO.PhotoRequest;
import com.mahir.Entity.Car;

public interface ICarController {

    public Car saveCar(Car car);

    public List<Car> getAllCars();

    public void Carphotosave(PhotoRequest photoRequest);

    public List<CarWithOwnerDTO> getCarsForPurchase(int userId);

    public List<CarWithOwnerDTO> getFilteredCars(int userId, CarFilterRequest filter);
    public CarWithOwnerDTO getMinPriceCar(int userId, String currencyCode);
    public CarWithOwnerDTO getMaxPrice(int userId, String currencyCode);

}