package com.mahir.Entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name="Address")
@Entity
public class Address {
    private String Address_title;

    @OneToOne
    @JoinColumn(name="person_id",nullable=false,unique=true)
    
    private Person person;

}
