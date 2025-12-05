package com.mahir.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IGalleristCarController;
import com.mahir.Entity.GalleristCar;
import com.mahir.Service.GalleristCarService;


@RestController
@RequestMapping("/api/gallerist-car")
@CrossOrigin(origins = "http://localhost:5174") 

public class GalleristCarController implements IGalleristCarController {

    @Autowired
    private GalleristCarService galleristCarService;

    @Override
    @PostMapping("/save")
    public GalleristCar saveGalleristCar(@RequestBody GalleristCar galleristCar) {
        return galleristCarService.saveGalleristCar(galleristCar);
    }

    @Override
    @GetMapping("/all")
    public List<GalleristCar> getAllGalleristCars() {
        return galleristCarService.getAllGalleristCars();
    }
}
