package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ISaledCarController;
import com.mahir.DTO.SaledDTO;
import com.mahir.DTO.SaledDTO_IU;
import com.mahir.Service.SaledService;

@RestController
@RequestMapping("/Saled/Api")
@CrossOrigin(origins = "http://localhost:5173") 

public class SaledCarController implements  ISaledCarController {

    @Autowired
    private SaledService saledService;

    @PostMapping("/SaledCar")
    @Override
    public SaledDTO PostSaled_Car(@RequestBody SaledDTO_IU saledDTO_IU) {
     return saledService.PostSaled_Car(saledDTO_IU);
    }
 
    
}
