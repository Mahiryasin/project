package com.mahir.Controller.imp;

import org.springframework.web.bind.annotation.RequestBody;

import com.mahir.Entity.Customer;

public interface ICustomerController {
    public Customer saveCustomer( Customer customerentity);

}
