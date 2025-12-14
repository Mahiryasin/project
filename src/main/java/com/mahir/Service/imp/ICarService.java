package com.mahir.Service.imp;

import java.math.BigDecimal;
import java.util.List;

import com.mahir.DTO.CarFilterRequest;
import com.mahir.DTO.CarWithOwnerDTO;
import com.mahir.Entity.Car;

public interface ICarService {
    public Car saveCar(Car car);

    public List<Car> changeCarDTO(List<Car> cars);

    public List<Car> getAllCars();

    public List<CarWithOwnerDTO> getCarsNotOwnedByUser(int userId);
    public List<CarWithOwnerDTO> getFilteredCars(int userId, CarFilterRequest filter);
    public CarWithOwnerDTO getMinPriceCar(int userId, String currencyCode);
    public CarWithOwnerDTO getMaxPrice(int userId, String currencyCode);

}
