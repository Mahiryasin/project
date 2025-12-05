package com.mahir.Controller;

import com.mahir.DTO.AddressDetailDTO;
import com.mahir.Entity.Address;
import com.mahir.Service.AddressService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mahir.Controller.imp.IAddressController;

@RestController
@RequestMapping("/Rest/Api")
@CrossOrigin(origins = "http://localhost:5174") 
public class AddressController implements  IAddressController {

    @Autowired
    private AddressService addressService;

    @Override
    @PostMapping("/SaveAddress")
    public Address saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

  
}