package com.mahir.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IGalleristController;
import com.mahir.Entity.Gallerist;
import com.mahir.Service.imp.IGalleristService;

@RestController
@RequestMapping("/api/gallerists")
@CrossOrigin(origins = "http://localhost:5173")

public class GalleristController implements  IGalleristController {

    private final IGalleristService galleristService;

    public GalleristController(IGalleristService galleristService) {
        this.galleristService = galleristService;
    }

    @Override
    @PostMapping("/save")
    public Gallerist saveGallerist(@RequestBody Gallerist gallerist) {
    return  galleristService.saveGallerist(gallerist);
      
    }

    @GetMapping("/all")
    public List getAllGallerists() {
       return galleristService.getAllGallerists();
     
    }

    @GetMapping("/get-gallerist/{carId}")
    public ResponseEntity<Gallerist> getGallerist(@PathVariable int carId) {
        Gallerist gallerist = galleristService.getGalleristByCarId(carId);
        
        if (gallerist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gallerist);
    }



   

   
}