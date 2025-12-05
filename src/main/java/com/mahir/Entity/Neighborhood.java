package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Neighborhood {

    private Integer id;
    private LocalDateTime createDate;
    private String neighborhoodName;
    private int districtId;

}
