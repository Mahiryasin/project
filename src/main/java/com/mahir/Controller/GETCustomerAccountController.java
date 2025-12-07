package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IGetCustomerAccountController;
import com.mahir.DTO.CustomerAccountDTO;
import com.mahir.Service.imp.IAccountService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") 
public class GETCustomerAccountController implements IGetCustomerAccountController {

    @Autowired
    private IAccountService customerAccountService;

    @Override
    @GetMapping("/customer-account/{userId}")
    public CustomerAccountDTO getCustomerAccountByUserId(@PathVariable long userId) {
        return customerAccountService.getCustomerAccountByUserId(userId);
    }
}