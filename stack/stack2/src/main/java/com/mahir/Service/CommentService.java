package com.mahir.Service;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.reader.StreamReader;

import com.mahir.DTO.CommentDTO;
import com.mahir.DTO.CommentDTO_IU;
import com.mahir.Entitites.Comment;
import com.mahir.Entitites.Post;
import com.mahir.Entitites.UserEntitiy;
import com.mahir.Mapper.CommentMapper;
import com.mahir.Reporistory.CommentReporistory;
import com.mahir.Reporistory.PostReporistory;
import com.mahir.Reporistory.UserReporistory;
import com.mahir.Service.imp.ICommentService;

@Service
public class CommentService implements ICommentService {

    private final CommentReporistory commentReporistory;
    private final CommentMapper commentMapper;
    private final UserReporistory userReporistory;
    private final PostReporistory postReporistory;

    public CommentService(CommentReporistory commentReporistory,CommentMapper commentMapper,UserReporistory userReporistory,PostReporistory postReporistory){
        this.commentReporistory=commentReporistory;
        this.commentMapper=commentMapper;
        this.userReporistory=userReporistory;
        this.postReporistory=postReporistory;
    }

    @Override
    public List<CommentDTO> FindCommentWithParam(Long postId, Long userId) {
        if(postId!=null && userId==null){
         commentMapper.TurnCommentDTOs(CommentFindByPostId(postId)); 
        }
       else if(postId==null && userId!=null){
          return commentMapper.TurnCommentDTOs(CommentFindByUserId(userId));
        }
       else if(postId!=null && userId!=null){
        return commentMapper.TurnCommentDTOs(CommentFindByUserIdAndpostId(postId, userId));
       }
       else{
        return commentMapper.TurnCommentDTOs(commentReporistory.findAll());
       }
       return null;
}

    public List<Comment> CommentFindByPostId(Long postId){
       
       return commentReporistory.FindCommentByPostId(postId);
    }
    
    public List<Comment> CommentFindByUserId(Long userId){
       return commentReporistory.FindCommentByUserId(userId);
    }
    public List<Comment> CommentFindByUserIdAndpostId(Long postId,Long userId){
        return commentReporistory.FindCommentByPostIdAndUserId(postId, userId);
    }

    @Override
    public CommentDTO findCommentById(Long commentId) {
      Optional<Comment>comment=  commentReporistory.findById(commentId);
      if(comment.isPresent()){
        return commentMapper.turnCommentDTO(comment.get());
      }
      else{
        return null;
      }
    }

    @Override
    public boolean DeleteCommentById(Long commentId) {
    Optional<Comment>comment=commentReporistory.findById(commentId);
    if(comment.isPresent()){
        commentReporistory.deleteById(commentId);
        return true;
    }
    return false;
    }

    @Override
    public CommentDTO PostComment(CommentDTO_IU commentDTO_IU) {
        Comment comment=new Comment();
      Optional<UserEntitiy>opUser=userReporistory.findById(commentDTO_IU.getUser_id());
     Optional<Post>opPost=postReporistory.findById(commentDTO_IU.getPost_id());
     if(opUser.isPresent() && opPost.isPresent()){
        BeanUtils.copyProperties(commentDTO_IU, comment);
        comment.setPost(opPost.get());
        comment.setUser(opUser.get());
      Comment save_Comment=commentReporistory.save(comment);
     return  commentMapper.turnCommentDTO(save_Comment);


     }
     return null;

    }

    @Override
    public CommentDTO UpdateComment(CommentDTO_IU commentDTO_IU,Long commentId) {
      Optional<Comment>opComment=commentReporistory.findById(commentId);
      if(opComment.isPresent()){
        Comment comments=opComment.get();
      Optional<UserEntitiy>opUser=userReporistory.findById(commentDTO_IU.getUser_id());
      Optional<Post>opPost=postReporistory.findById(commentDTO_IU.getPost_id());
      if(opUser.isPresent() && opPost.isPresent()){
        BeanUtils.copyProperties(commentDTO_IU, comments);
        comments.setPost(opPost.get());
        comments.setUser(opUser.get());
     Comment save_Comment=commentReporistory.save(comments);
      return  commentMapper.turnCommentDTO(save_Comment);
      }
    return null;
      }
      return null;
    }
    

    


}
