package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class CarImage {

    private int id;
    
    private int carId;       
    
    private String imageUrl; 
    
    private LocalDateTime createDate; 
}