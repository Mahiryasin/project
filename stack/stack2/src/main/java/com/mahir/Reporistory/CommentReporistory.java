package com.mahir.Reporistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mahir.Entitites.Comment;

@Repository
public interface CommentReporistory extends  JpaRepository<Comment, Long> {
    @Query(value="SELECT * FROM comment where post_id=?1",nativeQuery=true)
    public List<Comment> FindCommentByPostId(Long postId);

     @Query(value="SELECT * FROM comment where user_id=?1",nativeQuery=true)
    public List<Comment> FindCommentByUserId(Long user_id);

     @Query(value="SELECT * FROM comment where post_id=?1 And user_id=?2",nativeQuery=true)
    public List<Comment> FindCommentByPostIdAndUserId(Long postId,Long userId);



    


    


}
