package com.blog.simpleblog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.blog.simpleblog.VO.Post;
import com.blog.simpleblog.VO.Result;
import com.blog.simpleblog.postservice.PostService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PostController {
    
    @Autowired
    PostService postService;

    @GetMapping("/post")
    public Post getPost(@RequestParam("id")Long id) {
        Post post = postService.getPost(id);
        return  post;
    }
    
    @GetMapping("/posts")
    public List<Post> getPosts() {
            List<Post> posts = postService.getPosts();
            return  posts;
    }

    @GetMapping("/post/{id}")
    public Post gePostPathParam(@PathVariable("id")Long id) {
        Post post = postService.getPost(id);
        return post;
    }
    
    @GetMapping("/posts/updtdate/asc")
    public List<Post> getPostsOrderByUpdtAsc() {
        List<Post> posts = postService.getPosts();
        return posts;
    }

    @GetMapping("/posts/regdate/desc")
    public List<Post> getPostsOrderByRegDesc() {
        List<Post> posts = postService.getPosts();
        return posts;
    }
    
    @GetMapping("posts/search/title")
    public List<Post> searchByTitle(@RequestParam("query") String query) {
        List<Post> posts = postService.searchPostByTitle(query);
        return posts;
    }
    
    @GetMapping("posts/search/content")
    public List<Post> searchByContent(@RequestParam("query") String query) {
        List<Post> posts = postService.searchPostByContent(query);
        return posts;
    }

    @PostMapping("/post")
    public Object savePost (HttpServletResponse response, @RequestBody Post postParam) {
        Post post = new Post(postParam.getUser(), postParam.getTitle(), postParam.getContent());
        boolean isSuccess = postService.savePost(post);
        
        if(isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Fail");
        }
    }
    
    @DeleteMapping("/post")
    public Object deletePost(HttpServletResponse response, @RequestParam("id") Long id){
        boolean isSuccess = postService.deletePost(id);

        //log.info("id ::: " + id);

        if(isSuccess){
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Fail");
        }
    }

    @PutMapping("/post")
    public Object modifyPost(HttpServletResponse response, @RequestBody Post postParam) {
        Post post = new Post(postParam.getId(), postParam.getTitle(), postParam.getContent());
        boolean isSuccess = postService.updatePost(post);

        if(isSuccess){
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Fail");
        }
        
        
    }
}
