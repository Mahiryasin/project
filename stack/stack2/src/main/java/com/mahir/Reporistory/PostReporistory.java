package com.mahir.Reporistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mahir.Entitites.Post;

@Repository
public interface PostReporistory extends JpaRepository<Post, Long> {
    @Query(value="SELECT * FROM post where user_id=?1",nativeQuery=true)
    public List<Post> UserPosts(Long id);

}
