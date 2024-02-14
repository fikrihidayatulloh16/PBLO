package com.blog.simpleblog.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.simpleblog.VO.Comment;


public interface CommentJpaRepository extends JpaRepository<Comment, Serializable> {
    
}
