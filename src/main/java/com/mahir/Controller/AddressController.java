package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IAddressController;
import com.mahir.Entity.Address;
import com.mahir.Service.AddressService;

@RestController
@RequestMapping("/Rest/Api")
@CrossOrigin(origins = "http://localhost:5173") 
public class AddressController implements  IAddressController {

    @Autowired
    private AddressService addressService;

    @Override
    @PostMapping("/SaveAddress")
    public Address saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

  
}