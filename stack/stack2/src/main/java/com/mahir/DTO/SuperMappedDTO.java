package com.mahir.DTO;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SuperMappedDTO {

    private Long id;

    
   private String CreatedBy;

  
   private LocalDateTime CreateDate;

  
   private String LastModifiedBy;

 
   private LocalDateTime LastModifiedDate;

}
