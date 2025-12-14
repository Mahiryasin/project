package com.mahir.DTO;

import com.mahir.Entity.Car;

import lombok.Data;

@Data
public class CarWithOwnerDTO {
    private Car car;
    private Integer ownerUserId;
    private String firstImageUr;
}
