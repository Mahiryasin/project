package com.mahir.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mahir.DTO.PostDTO;
import com.mahir.Entitites.Post;

@Mapper(componentModel="spring")
public interface  PostMapper{


    @Mapping(source="user",target="userDTO")

    List<PostDTO>turnDTOs(List<Post>post);


    @Mapping(source="user",target="userDTO")
    public PostDTO turnDTO(Post post);


  


     

}
