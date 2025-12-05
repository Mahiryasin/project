package com.mahir.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Account {
     private Integer id;
    private LocalDateTime createDate;
    private String accountNo;
    private String iban;
    private BigDecimal amount;
    private String currencyCode;

}
