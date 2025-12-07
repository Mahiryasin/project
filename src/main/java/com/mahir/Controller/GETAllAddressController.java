package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IGetAllAddressController;
import com.mahir.DTO.AddressDetailDTO;
import com.mahir.Service.AddressService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") 
public class GETAllAddressController implements IGetAllAddressController {

   @Autowired
    private AddressService addressService;
  @Override
    @GetMapping("/user/{userId}")
    public AddressDetailDTO getAddressByUserId(@PathVariable long userId) {
        return  addressService.getAddressByUserId(userId);
        }
}
