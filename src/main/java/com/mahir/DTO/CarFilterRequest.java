package com.mahir.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarFilterRequest {
    private Integer minYear;    
     private Integer maxYear;  
     private BigDecimal minPrice;
    private BigDecimal maxPrice;     
    private String cityName;
    private String currencyCode;

}
