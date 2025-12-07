package com.mahir.Controller.imp;

import org.springframework.web.bind.annotation.PathVariable;

import com.mahir.DTO.CustomerAccountDTO;

public interface IGetCustomerAccountController {
    CustomerAccountDTO getCustomerAccountByUserId(@PathVariable long userId);
}