package com.blog.simpleblog.postservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.simpleblog.VO.Post;
import com.blog.simpleblog.repository.PostJpaRepository;
import com.blog.simpleblog.repository.PostRepository;
import com.mysql.cj.util.StringUtils;

@Service
public class PostService {
    private static List<Post> posts;
    
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostJpaRepository jpaRepository;

    public Post getPost(Long id){
        Post post = jpaRepository.findOneById(id);

        return post;
    }

    public Post getPostsOrderByUpdtAsc(Date updtDate){
        Post post = postRepository.findPostOrderByUpdtDateAsc(updtDate);

        return post;
    }

    public Post getPostsOrderByRegDesc(Date regDate){
        Post post = postRepository.findPostOrderByRegDateDesc(regDate);
        return post;
    }

    public List<Post> getPosts(){
            List<Post> posts = jpaRepository.findAllByOrderByUpdtDateDesc();
            return  posts;
    }

    public List<Post> searchPostByTitle(String query){
        List<Post> posts = postRepository.findPostLikeTitle(query);
        return posts;
    }

    public List<Post> searchPostByContent(String query){
        List<Post> posts = postRepository.findPostLikeContent(query);
        return posts;
    }

    public boolean savePost(Post post){
        Post result = jpaRepository.save(post);
        boolean isSuccess = true;

        if(result == null){
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean deletePost(Long id){
        Post result = jpaRepository.findOneById(id);

        if (result == null){
            return false;
        }

        jpaRepository.deleteById(id);
        return true;
    }

    public boolean updatePost(Post post){
        Post result = jpaRepository.findOneById(post.getId());

        if(result == null){
            return false;
        }

        if(!StringUtils.isNullOrEmpty(post.getTitle())){
            result.setTitle(post.getTitle());
        }

        if (!StringUtils.isNullOrEmpty(post.getContent())){
            result.setContent(post.getContent());
        }

        jpaRepository.save(result);

        return true;
    }
}
