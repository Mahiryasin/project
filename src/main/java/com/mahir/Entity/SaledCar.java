package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SaledCar {
    private Integer id;
    private LocalDateTime createDate;
    private Integer galleristId;
    private int carId;
    private int customerId;

}
