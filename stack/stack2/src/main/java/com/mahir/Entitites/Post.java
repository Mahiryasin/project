package com.mahir.Entitites;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.websocket.OnOpen;
import lombok.Data;

@Entity
@Table(name = "Post",uniqueConstraints=@UniqueConstraint(columnNames={"id","user_id"}))
@Data
public class Post extends  SuperMappeds {

  

    @Column(name="title",nullable=false)
    private String Title;

    @Column(name="text",nullable=false)
    private String Text;

    
    
    @ManyToOne(fetch = FetchType.EAGER) // !! user null olarak gelicek direk cekilmicek !!                                   
    @OnDelete(action = OnDeleteAction.CASCADE)// silerlen bagımlı oldugunuda sil
    @JoinColumn(name="user_id",nullable=false)// diger tablodan alıcak joincolumn kullanılmalı
    private UserEntitiy user;

}
