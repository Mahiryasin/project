package com.mahir.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class Customer {

     private Integer id;
    private LocalDateTime createDate;
    private String firstName;
    private String lastName;
    private String tckn;
    private LocalDate birthOfDate;
    private int addressId;
    private int accountId;
    private int userId;

}
