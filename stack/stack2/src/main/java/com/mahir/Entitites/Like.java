package com.mahir.Entitites;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Likes")
@Data
public class Like extends  SuperMappeds{

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    
    private Post post_id;

    @JoinColumn(name = "user_id",nullable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private UserEntitiy User;
}
