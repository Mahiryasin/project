package com.mahir.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mahir.DTO.LikeDTO;
import com.mahir.DTO.LikeDTO_IU;
import com.mahir.Entitites.Like;
import com.mahir.Entitites.Post;
import com.mahir.Entitites.UserEntitiy;
import com.mahir.Mapper.LikeMapper;
import com.mahir.Reporistory.LikeReporistory;
import com.mahir.Reporistory.PostReporistory;
import com.mahir.Reporistory.UserReporistory;
import com.mahir.Service.imp.ILikeService;

@Service
public class LikeService implements ILikeService {

    private final LikeReporistory likeReporistory;
    private final LikeMapper likeMapper;
    private final PostReporistory postReporistory;
    private final UserReporistory userReporistory;

    public LikeService(LikeReporistory likeReporistory, LikeMapper likeMapper, PostReporistory postReporistory,
            UserReporistory userReporistory) {
        this.likeReporistory = likeReporistory;
        this.likeMapper = likeMapper;
        this.postReporistory = postReporistory;
        this.userReporistory = userReporistory;
    }

    @Override
    public List<LikeDTO> FindLikeWithParam(Long postId, Long userId) {
        if (postId != null && userId == null) {
            return likeMapper.turnLikeDTOs(LikeFindByPostId(postId));
        } else if (postId == null && userId != null) {
            return likeMapper.turnLikeDTOs(LikeFindByUserId(userId));
        } else if (postId != null && userId != null) {
            return likeMapper.turnLikeDTOs(likeReporistory.FindLikeByPostIdAndUserId(postId, userId));
        }

        return null;
    }

    public List<Like> LikeFindByPostId(Long postId) {
        return likeReporistory.FindLikeByPostId(postId);
    }

    public List<Like> LikeFindByUserId(Long userId) {
        return likeReporistory.FindLikeByUserId(userId);
    }

    @Override
    public LikeDTO findLikeById(Long likeId) {
        Optional<Like> opt = likeReporistory.findById(likeId);
        if (opt.isPresent()) {
            return likeMapper.turnLikeDTO(opt.get());
        }
        return null;
    }

    @Override
    public boolean DeleteLikeById(Long likeId) {
        Optional<Like> opt = likeReporistory.findById(likeId);
        if (opt.isPresent()) {
            likeReporistory.delete(opt.get());
            return true;
        }
        return false;
    }

    @Override
    public LikeDTO PostLike(LikeDTO_IU likeDTO_IU) {
        Optional<Post> post = postReporistory.findById(likeDTO_IU.getPost_id());
        Optional<UserEntitiy> user = userReporistory.findById(likeDTO_IU.getUser_id());
        if (post.isPresent() && user.isPresent()) {
            Like like = new Like();
            like.setPost_id(post.get());
            like.setUser(user.get());
            Like saved = likeReporistory.save(like);
            return likeMapper.turnLikeDTO(saved);
        }
        return null;
    }

    @Override
    public LikeDTO UpdateLike(LikeDTO_IU likeDTO_IU, Long likeId) {
        Optional<Like> opt = likeReporistory.findById(likeId);
        if (opt.isPresent()) {
            Like like = opt.get();
            if (likeDTO_IU.getPost_id() != null) {
                postReporistory.findById(likeDTO_IU.getPost_id()).ifPresent(p -> like.setPost_id(p));
            }
            if (likeDTO_IU.getUser_id() != null) {
                userReporistory.findById(likeDTO_IU.getUser_id()).ifPresent(u -> like.setUser(u));
            }
            Like saved = likeReporistory.save(like);
            return likeMapper.turnLikeDTO(saved);
        }
        return null;
    }

}
