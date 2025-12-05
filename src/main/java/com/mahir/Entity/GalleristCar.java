package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class GalleristCar {

    
    private Integer id;
    private LocalDateTime createDate;
    private int galleristId;
    private int carId;

}
