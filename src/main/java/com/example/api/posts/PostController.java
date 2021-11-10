package com.example.api.posts;

import com.example.api.authors.Author;
import com.example.api.categories.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path="api/posts", consumes="application/json")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // api get all posts
    @GetMapping
    public List<Post> getAllPost(){
        return postService.getPosts();
    }

    // api get detailed post
    @GetMapping(path = "{postId}")
    public Post getAPost(@PathVariable("postId") Long postId){
        return postService.getDetailedPost(postId);
    }

    //api post
    @PostMapping
    public Post addAPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    // api delete
    @DeleteMapping(path = "{postId}")
    public Map<String, String> deleteAPost(@PathVariable("postId") Long postId){
        return postService.deletePost(postId);
    }

    // put
    @PutMapping(path =  "{postId}")
    public Post updateAPost(@PathVariable("postId") Long postId,
                            @RequestBody Post postt) {
        return postService.updatePost(postId, postt);
    }

}
