package com.mahir.Entitites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="person")
public class Person {

    private String name;

    private String surname;

    private String tc;

    @OneToOne(mappedBy="person",cascade=CascadeType.ALL)
    private Address address;

}
