package com.mahir.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Gallerist {
     private Integer id;
    private LocalDateTime createDate;
    private String firstName;
    private String lastName;
    private int addressId;
    private int userId;

}
