package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class District {

    private Integer id;
    private LocalDateTime createDate;
    private String districtName;
    private int cityId;

}
