package com.mahir.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class Car {

     private int id;
    private LocalDateTime createDate;
    private String plaka;
    private String brand;
    private String model;
    private int productionYear;
    private BigDecimal price;
    private BigDecimal damagePrice;
    private String currencyCode;
    private String statusCode;


}
