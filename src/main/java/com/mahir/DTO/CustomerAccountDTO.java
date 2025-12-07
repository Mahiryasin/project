package com.mahir.DTO;

import java.math.BigDecimal;
import java.sql.Date;

public class CustomerAccountDTO {
    private String firstName;
    private String lastName;
    private String tckn;
    private Date birthOfDate;
    private String accountNo;
    private String iban;
    private BigDecimal amount;
    private String currencyCode;
    public CustomerAccountDTO(String firstName, String lastName, String tckn, Date birthOfDate, String accountNo,
            String iban, BigDecimal amount, String currencyCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tckn = tckn;
        this.birthOfDate = birthOfDate;
        this.accountNo = accountNo;
        this.iban = iban;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getTckn() {
        return tckn;
    }
    public void setTckn(String tckn) {
        this.tckn = tckn;
    }
    public Date getBirthOfDate() {
        return birthOfDate;
    }
    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}

