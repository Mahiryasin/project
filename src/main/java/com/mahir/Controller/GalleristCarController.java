package com.mahir.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IGalleristCarController;
import com.mahir.DTO.GalleristCarDetailsDTO;
import com.mahir.DTO.GalleristCarRequest;
import com.mahir.Entity.GalleristCar;
import com.mahir.Service.GalleristCarService;


@RestController
@RequestMapping("/api/gallerist-car")
@CrossOrigin(origins = "http://localhost:5173") 

public class GalleristCarController implements IGalleristCarController {

    @Autowired
    private GalleristCarService galleristCarService;

   
   

    @Override
    @GetMapping("/all")
    public List<GalleristCar> getAllGalleristCars() {
        return galleristCarService.getAllGalleristCars();
    }

    @Override
    @PostMapping("/save")

    public GalleristCar saveGalleristCar(@RequestBody GalleristCarRequest galleristCarRequest) {
       return galleristCarService.saveGalleristCar(galleristCarRequest);
    }

  
 @Override
 @GetMapping("/my-cars/{userId}")
public List<GalleristCarDetailsDTO> getMySalableCars(@PathVariable int userId) {
    return galleristCarService.getSalableCarsByUserId(userId);
}






@Override
@GetMapping("/count/{userId}")

public int getSalableCarCountByUserId(@PathVariable int userId) {
      return galleristCarService.getSalableCarCountByUserId(userId);

}

@GetMapping("/order/new-old/{userId}")
@Override
public List<GalleristCarDetailsDTO> getNewestCarsByUserId(@PathVariable int userId) {
   return galleristCarService.getNewestCarsByUserId(userId);
}

@GetMapping("/order/old-new/{userId}")

@Override
public List<GalleristCarDetailsDTO> getOldestCarsByUserId(@PathVariable int userId) {
    return galleristCarService.getOldestCarsByUserId(userId);
}

@DeleteMapping("/delete/{carId}")
@Override
public void deleteCarById(@PathVariable int carId) {
   galleristCarService.deleteCarById(carId);
}}
