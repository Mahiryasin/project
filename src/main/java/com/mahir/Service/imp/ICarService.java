package com.mahir.Service.imp;

import java.util.List;

import com.mahir.Entity.Car;

public interface ICarService {
public Car saveCar(Car car);

public List<Car> changeCarDTO(List<Car> cars);
}
