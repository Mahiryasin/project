package com.mahir.Controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.*;

import com.mahir.Controller.imp.IPostController;
import com.mahir.DTO.PostDTO;
import com.mahir.DTO.PostDTO_IU;
import com.mahir.Service.imp.IPostService;

@RestController
@RequestMapping("/Request/Api")
@CrossOrigin("http://localhost:5175")

public class PostController implements IPostController {
    
    private final IPostService postService;


    public PostController(IPostService postService){
        this.postService = postService;
    
    }

    @PostMapping("/SavePost")
    @Override
    public PostDTO SavePost(@RequestBody PostDTO_IU postDTO_IU) {
       return postService.SavePost(postDTO_IU);
    }

    @GetMapping("/GetallPosts")
	@Override
	public List<PostDTO> GetallPosts() {
		return postService.GetallPosts();
	}

    @GetMapping("/findPost/{id}")
    @Override
    public PostDTO FindPostById(@PathVariable(name="id") Long id) {
      return postService.FindPostById(id);
    }

    @DeleteMapping("/delete/Post/{id}")
    @Override
    public boolean deletePostById(@PathVariable(name="id") Long id) {
        return postService.deletePostById(id);
    }

    @GetMapping("/PostofUser")
    @Override
    public List<PostDTO> PostofUser(@RequestParam(name=
    "id") Long UserId) {
       
       return postService.PostofUser(UserId);
    }

    @PutMapping("/PutPost")
    @Override
    public PostDTO UpdatePost(@RequestBody PostDTO_IU postDTO_IU) {
      return postService.UpdatePost(postDTO_IU);
    }
    

}
