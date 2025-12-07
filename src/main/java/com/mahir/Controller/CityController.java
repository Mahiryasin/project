package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ICityController;
import com.mahir.Entity.City;
import com.mahir.Service.imp.ICityService;

@RestController
@RequestMapping("/api/city")
@CrossOrigin(origins = "http://localhost:5173")
public class CityController implements ICityController {

    @Autowired
    private ICityService cityService;

    @PostMapping("/save")
    @Override
    public City saveCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }
}
