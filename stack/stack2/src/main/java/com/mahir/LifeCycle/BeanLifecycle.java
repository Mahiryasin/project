package com.mahir.LifeCycle;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class BeanLifecycle {

    @PreDestroy
    public void BeanDestroy(){
        System.out.println("bean destroy");
    }
    @PostConstruct
    public void PostConstruct(){
        System.out.println("post construct");
    }
    @PostLoad
    public void load(){
       System.out.println("veri tabanı yuklendi !!");
    }

    @PrePersist
    public void Persist(){
        System.out.println("insert islemi basladı !!");
    }
    @PostPersist
    public void Postpersist(){
        System.out.println("insert islemi sonlandı !!");
    }

    @PreUpdate
    public void PreUpdate(){
        System.out.println("update islemi basladı !!");

    }
    @PostUpdate
    public void PostUpdate(){
        System.out.println("update islemi sonlandı !!");
    }
    @PreRemove
    public void PreRemove(){
        System.out.println("delete isleminden once");
    }
    @PostRemove
    public void PostRemove(){
        System.out.println("delete isleminden sonra ");
    }

}
