package com.mahir.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.ICommentController;
import com.mahir.DTO.CommentDTO;
import com.mahir.DTO.CommentDTO_IU;
import com.mahir.Service.imp.ICommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin("http://localhost:5173")
public class CommentController implements ICommentController {

    private final ICommentService commentService;

    public CommentController(ICommentService commentService){
        this.commentService=commentService;
    }

    @GetMapping
    @Override
    public List<CommentDTO> FindCommentWithParam(@RequestParam(name="postId") Long postId,@RequestParam(name="userId") Long userId) {
       return commentService.FindCommentWithParam(postId, userId);
    }

    @Override
    public CommentDTO findCommentById(Long commentId) {
        return commentService.findCommentById(commentId);
    }

    @Override
    public boolean DeleteCommentById(Long commentId) {
      return commentService.DeleteCommentById(commentId);
    }

    @Override
    public CommentDTO PostComment(CommentDTO_IU commentDTO_IU) {
       return commentService.PostComment(commentDTO_IU);
    }

    @Override
    public CommentDTO UpdateComment(CommentDTO_IU commentDTO_IU,Long commentId) {
        // TODO Auto-generated method stub
       return null;
    }

    


   
    

}
