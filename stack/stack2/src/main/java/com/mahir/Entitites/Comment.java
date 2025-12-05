package com.mahir.Entitites;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comment")
@Data

public class Comment extends SuperMappeds {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE) // silerlen bagımlı oldugunuda sil
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY)
    
    @OnDelete(action = OnDeleteAction.CASCADE)// silerlen bagımlı oldugunuda sil
    @JoinColumn(name="user_id")
    private UserEntitiy user;

    @Column(name = "text")
    
    private String text;
   

}
