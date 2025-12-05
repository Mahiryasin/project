package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Address {

    private Integer id;
    private LocalDateTime createDate;
    private int neighborhoodId;
    private String street;

}
