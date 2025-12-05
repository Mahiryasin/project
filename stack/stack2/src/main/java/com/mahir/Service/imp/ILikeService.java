package com.mahir.Service.imp;

import java.util.List;

import com.mahir.DTO.LikeDTO;
import com.mahir.DTO.LikeDTO_IU;

public interface ILikeService {

    public List<LikeDTO> FindLikeWithParam(Long postId, Long userId);

    public LikeDTO findLikeById(Long likeId);

    public boolean DeleteLikeById(Long likeId);

    public LikeDTO PostLike(LikeDTO_IU likeDTO_IU);

    public LikeDTO UpdateLike(LikeDTO_IU likeDTO_IU, Long likeId);

}
