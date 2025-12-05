package com.mahir.DTO;

import com.mahir.Entitites.Post;
import com.mahir.Entitites.UserEntitiy;

import lombok.Data;

@Data
public class CommentDTO_IU extends SuperMappedDTO {

     private Long post_id;

    private Long user_id;

    private String text;
}
