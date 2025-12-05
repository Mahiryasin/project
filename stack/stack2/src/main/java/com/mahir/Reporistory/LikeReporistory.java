package com.mahir.Reporistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mahir.Entitites.Like;

@Repository
public interface LikeReporistory extends JpaRepository<Like, Long> {

	@Query(value = "SELECT * FROM Likes where post_id=?1", nativeQuery = true)
	public List<Like> FindLikeByPostId(Long postId);

	@Query(value = "SELECT * FROM Likes where user_id=?1", nativeQuery = true)
	public List<Like> FindLikeByUserId(Long user_id);

	@Query(value = "SELECT * FROM Likes where post_id=?1 And user_id=?2", nativeQuery = true)
	public List<Like> FindLikeByPostIdAndUserId(Long postId, Long userId);

}
