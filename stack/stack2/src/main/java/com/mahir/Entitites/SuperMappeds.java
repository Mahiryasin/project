package com.mahir.Entitites;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class SuperMappeds {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @CreatedBy
   @Column(updatable=false)
   private String CreatedBy;

   @CreatedDate
   @Column(updatable=false)
   private LocalDateTime CreateDate;

   @LastModifiedBy
   @Column(insertable=false)
   private String LastModifiedBy;

   @org.springframework.data.annotation.LastModifiedDate
   @Column(insertable=false)
   private LocalDateTime LastModifiedDate;


  

}
