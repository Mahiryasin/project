package com.mahir.Entitites;

import org.springframework.context.annotation.Scope;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "User_")
@Data
@Scope("request")
public class UserEntitiy extends SuperMappeds {

 

    @Column(name="username")
    private String Username;

    @Column(name="password")
    private String Password;

    @PrePersist
    public void PrePersist() {
        System.out.println("user is created !!");
    }
    
    @PreRemove
    
    public void remove() {
        System.out.println("user is deleted");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("bean is destroy");
    }

}
