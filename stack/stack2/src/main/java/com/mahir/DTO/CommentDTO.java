package com.mahir.DTO;

import lombok.Data;

@Data
public class CommentDTO {

    private PostDTO postDTO;

    private UserDTO userDTO;

    
    private String text;

}
