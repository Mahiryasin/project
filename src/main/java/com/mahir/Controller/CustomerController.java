package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ICustomerController;
import com.mahir.Entity.Customer;
import com.mahir.Service.imp.ICustomerService;


@RestController
@RequestMapping("/Request/Mapping")
@CrossOrigin(origins = "http://localhost:5173") 

public class CustomerController implements ICustomerController {

    @Autowired
    private ICustomerService customerService;

    @Override
    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customerentity) {
      return  customerService.saveCustomer(customerentity);
        
    }
    
   

}
