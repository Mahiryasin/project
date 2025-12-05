package com.mahir.Controller.imp;

import java.util.List;

import com.mahir.Entity.Car;

public interface ICarController {

    public Car saveCar(Car car);

     public List<Car> getAllCars();
}
