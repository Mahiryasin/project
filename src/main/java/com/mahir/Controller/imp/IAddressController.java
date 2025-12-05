package com.mahir.Controller.imp;

import org.springframework.web.bind.annotation.PathVariable;

import com.mahir.DTO.AddressDetailDTO;
import com.mahir.Entity.Address;

public interface IAddressController {

    Address saveAddress(Address address);


}
