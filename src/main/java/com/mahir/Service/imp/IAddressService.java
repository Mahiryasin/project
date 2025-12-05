package com.mahir.Service.imp;

import com.mahir.DTO.AddressDetailDTO;
import com.mahir.Entity.Address;

public interface IAddressService {

    Address saveAddress(Address address);

    public AddressDetailDTO getAddressByUserId(long userId);

}
