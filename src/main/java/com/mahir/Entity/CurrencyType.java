package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class CurrencyType {
      private Integer id;
    private LocalDateTime createDate;
    private String currencyCode;
    private String description;

}
