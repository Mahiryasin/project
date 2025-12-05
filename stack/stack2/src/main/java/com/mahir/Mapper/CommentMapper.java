package com.mahir.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mahir.DTO.CommentDTO;
import com.mahir.Entitites.Comment;

@Mapper(componentModel="spring")
public interface CommentMapper {

    @Mapping(source="post",target="postDTO")
    @Mapping(source="user",target="userDTO")
    public CommentDTO turnCommentDTO(Comment Comment);

    @Mapping(source="post",target="postDTO")
    @Mapping(source="user",target="userDTO")
    public List<CommentDTO>TurnCommentDTOs(List<Comment>comments);
}
