package com.mahir.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IAccountController;
import com.mahir.Entity.Account;
import com.mahir.Service.imp.IAccountService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174") 

public class AccountController implements IAccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/saveAccount")
    @Override
    public Account saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }
}
