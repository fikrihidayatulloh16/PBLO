package com.blog.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.simpleblog.VO.Comment;
import com.blog.simpleblog.VO.Result;
import com.blog.simpleblog.postservice.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService CommentService;

    @PostMapping("/comment")
    public Object savePost(HttpServletResponse response, @RequestBody Comment commentParam) {
        Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment());
        boolean isSuccess = CommentService.saveComment(comment);

        if(isSuccess){
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Fail");
        }
    }
}
