package com.mahir.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ICarController;
import com.mahir.DTO.CarFilterRequest;
import com.mahir.DTO.CarWithOwnerDTO;
import com.mahir.DTO.PhotoRequest;
import com.mahir.Entity.Car;
import com.mahir.Service.CarImageService;
import com.mahir.Service.imp.ICarService;

@RestController
@RequestMapping("/api/car")
@CrossOrigin(origins = "http://localhost:5173")
public class CarController implements ICarController {

    @Autowired
    private ICarService carService;

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

    @Override
    @GetMapping("/for-purchase/{userId}")
    public List<CarWithOwnerDTO> getCarsForPurchase(@PathVariable int userId) {
        System.out.println("iceriye girildi DWMOWMDMWDW");
        return carService.getCarsNotOwnedByUser(userId);
    }

    @PostMapping("/PhotoSave")
    @Override
    public void Carphotosave(@RequestBody PhotoRequest photoRequest) {
        carImageService.saveCarImageRecord(photoRequest);
    }

   @PostMapping("/filter/{userId}") 
    @Override
    public List<CarWithOwnerDTO> getFilteredCars(@PathVariable(name="userId") int userId, @RequestBody CarFilterRequest filter) {
        return carService.getFilteredCars(userId, filter);
    }

 @GetMapping("/min-price/{userId}/{currency}")
   @Override
   public CarWithOwnerDTO getMinPriceCar(@PathVariable int userId,@PathVariable String currency) {
    
   return carService.getMinPriceCar(userId, currency);
   }

   @GetMapping("/max-price/{userId}/{currency}")
   @Override
   public CarWithOwnerDTO getMaxPrice(@PathVariable int userId,@PathVariable String currency) {
   return carService.getMaxPrice(userId, currency);
   }
}
