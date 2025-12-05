package com.mahir.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mahir.DTO.LikeDTO;
import com.mahir.Entitites.Like;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "post_id", target = "postDTO")
    @Mapping(source = "user", target = "userDTO")
    public LikeDTO turnLikeDTO(Like like);

    @Mapping(source = "post_id", target = "postDTO")
    @Mapping(source = "User", target = "userDTO")
    public List<LikeDTO> turnLikeDTOs(List<Like> likes);

}
