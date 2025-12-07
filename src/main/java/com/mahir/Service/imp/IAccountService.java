package com.mahir.Service.imp;

import com.mahir.DTO.CustomerAccountDTO;
import com.mahir.Entity.Account;

public interface IAccountService {
    Account saveAccount(Account account);
    public CustomerAccountDTO getCustomerAccountByUserId(long userId);
}
