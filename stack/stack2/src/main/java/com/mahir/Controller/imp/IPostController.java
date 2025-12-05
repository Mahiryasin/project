package com.mahir.Controller.imp;

import java.util.List;

import com.mahir.DTO.PostDTO;
import com.mahir.DTO.PostDTO_IU;

public interface IPostController {

       public  PostDTO SavePost(PostDTO_IU postDTO_IU);

       public List<PostDTO>GetallPosts();

       public PostDTO FindPostById(Long id);

       public boolean  deletePostById(Long id);

       public List<PostDTO> PostofUser(Long UserId);

       public PostDTO UpdatePost(PostDTO_IU postDTO_IU);

}
