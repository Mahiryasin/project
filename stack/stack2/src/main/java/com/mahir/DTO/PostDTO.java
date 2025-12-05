package com.mahir.DTO;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.mahir.Entitites.UserEntitiy;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PostDTO extends  SuperMappedDTO {
    private String Title;

   
    private String Text;

    private UserDTO userDTO;


}
