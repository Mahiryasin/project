package com.mahir.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ICarController;
import com.mahir.DTO.PhotoRequest;
import com.mahir.Entity.Car;
import com.mahir.Service.CarImageService;
import com.mahir.Service.CarService;

@RestController
@RequestMapping("/api/car")
@CrossOrigin(origins = "http://localhost:5173")
public class CarController implements  ICarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarImageService carImageService;


    @Override
    @PostMapping("/save")
    public Car saveCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

   
    @Override
    @GetMapping("/all")
    public List<Car> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return carService.changeCarDTO(cars);
    }


    @PostMapping("/PhotoSave")
    @Override
    public void Carphotosave(@RequestBody PhotoRequest photoRequest) {
       carImageService.saveCarImageRecord(photoRequest);
    }
}
