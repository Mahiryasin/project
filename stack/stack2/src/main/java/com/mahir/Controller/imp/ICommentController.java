package com.mahir.Controller.imp;

import java.util.List;

import com.mahir.DTO.CommentDTO;
import com.mahir.DTO.CommentDTO_IU;

public interface ICommentController {

    public List<CommentDTO> FindCommentWithParam(Long postId,Long userId);

    public CommentDTO findCommentById(Long commentId);

    public boolean DeleteCommentById(Long commentId);

    public CommentDTO PostComment(CommentDTO_IU commentDTO_IU);

    public CommentDTO UpdateComment(CommentDTO_IU commentDTO_IU,Long commentId);



}
