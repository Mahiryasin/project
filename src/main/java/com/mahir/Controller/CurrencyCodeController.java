package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ICurrencyCodeController;
import com.mahir.Entity.CurrencyType;
import com.mahir.Service.imp.ICurrencyCodeService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") 

public class CurrencyCodeController implements ICurrencyCodeController {

    private final ICurrencyCodeService currencyCodeService;

    @Autowired
    public CurrencyCodeController(ICurrencyCodeService currencyCodeService) {
        this.currencyCodeService = currencyCodeService;
    }

    @PostMapping("/saveCurrencyCode")
    @Override
    public CurrencyType saveCurrencyType(@RequestBody CurrencyType currencyType) {
        return currencyCodeService.saveCurrencyType(currencyType);
    }
}
