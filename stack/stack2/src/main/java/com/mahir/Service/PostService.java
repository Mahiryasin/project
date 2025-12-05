package com.mahir.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mahir.DTO.PostDTO;
import com.mahir.DTO.PostDTO_IU;
import com.mahir.DTO.UserDTO;
import com.mahir.Entitites.Post;
import com.mahir.Entitites.UserEntitiy;
import com.mahir.Mapper.PostMapper;
import com.mahir.Reporistory.PostReporistory;
import com.mahir.Reporistory.UserReporistory;
import com.mahir.Service.imp.IPostService;

@Service
public class PostService implements  IPostService{

    private final PostReporistory postReporistory;

    private final UserReporistory userReporistory;

    private final PostMapper postMapper;

    public PostService(PostReporistory postReporistory,UserReporistory userReporistory,PostMapper postMapper) {
        this.postReporistory = postReporistory;
        this.userReporistory=userReporistory;
        this.postMapper=postMapper;
    }

    @Override
    public PostDTO SavePost(PostDTO_IU postDTO_IU) {
    Optional<UserEntitiy>user=userReporistory.findById(postDTO_IU.getUser_id());
    PostDTO postDTO=new PostDTO();
    Post post=new Post();
    UserDTO userDTO=new UserDTO();
    if(user.isPresent()){
        BeanUtils.copyProperties(postDTO_IU, post);
        post.setUser(user.get());
        Post save_post=  postReporistory.save(post);

         BeanUtils.copyProperties(save_post, postDTO);
         BeanUtils.copyProperties(save_post.getUser(), userDTO);
         postDTO.setUserDTO(userDTO);
         

         return postDTO;
    }
    return null;
   
}

	@Override
	public List<PostDTO> GetallPosts() {
	 List<Post>PostList=postReporistory.findAll();
     return  postMapper.turnDTOs(PostList);

}

    @Override
    public PostDTO FindPostById(Long id) {
    Optional<Post>optional=postReporistory.findById(id);
    if(optional.isPresent()){
      return  postMapper.turnDTO(optional.get());
    }
    return null;
    }

    @Override
    public boolean deletePostById(Long id) {
      Optional<Post>optional=postReporistory.findById(id);
      if(optional.isPresent()){
        postReporistory.delete(optional.get());
        return true;
      }
      return false;

    }

    @Override
    public List<PostDTO> PostofUser(Long UserId) {
       List<Post>userPosts= postReporistory.UserPosts(UserId);

       return postMapper.turnDTOs(userPosts);
    }

    @Override
    public PostDTO UpdatePost(PostDTO_IU postDTO_IU) {
      
      Optional<Post>post=postReporistory.findById(postDTO_IU.getId());
      if(post.isPresent()){
        post.get().setText(postDTO_IU.getText());
        post.get().setTitle(postDTO_IU.getText());
       Post posts=postReporistory.save(post.get());
       return postMapper.turnDTO(posts);
      }
      return null;
    }
    

}
