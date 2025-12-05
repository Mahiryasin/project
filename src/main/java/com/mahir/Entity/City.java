package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class City {

    private Integer id;
    private LocalDateTime createDate;
    private String cityName;

}
