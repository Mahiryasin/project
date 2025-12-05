package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CarStatusType {
     private Integer id;
    private LocalDateTime createDate;
    private String statusCode;
    private String description;

}
