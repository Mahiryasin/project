package com.mahir.Controller.imp;

import com.mahir.DTO.AddressDetailDTO;

public interface IGetAllAddressController {
 public AddressDetailDTO getAddressByUserId(long userId);
}
