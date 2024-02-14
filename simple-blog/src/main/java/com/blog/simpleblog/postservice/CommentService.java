package com.blog.simpleblog.postservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.simpleblog.repository.CommentJpaRepository;
import com.blog.simpleblog.VO.Comment;

@Service
public class CommentService {
    
    @Autowired
    CommentJpaRepository commentJpaRepository;

    public boolean saveComment(Comment comment){
        Comment result = commentJpaRepository.save(comment);
        boolean isSuccess = true;

        if(result == null){
            isSuccess = false;
        }

        return isSuccess;
    }
}
