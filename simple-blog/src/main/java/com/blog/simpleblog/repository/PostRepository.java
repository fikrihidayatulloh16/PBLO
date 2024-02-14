package com.blog.simpleblog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blog.simpleblog.mapper.PostMapper;
import com.blog.simpleblog.VO.Post;

@Repository
public class PostRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Post findOne(Long id){
        String sql = "SELECT * FROM post WHERE id = ?"; // Correcting SQL query

        RowMapper<Post> rowMapper = new PostMapper();

        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public Post findPostOrderByUpdtDateAsc(Date updtDate){
        String sql = "SELECT * FROM post ORDER BY updt_date ASC";

        RowMapper<Post> rowMapper = new PostMapper();

        return this.jdbcTemplate.queryForObject(sql, rowMapper, updtDate);
    }

    public Post findPostOrderByRegDateDesc(Date regDate){
        String sql = "SELECT * FROM post ORDER BY reg_date Desc";

        RowMapper<Post> rowMapper = new PostMapper();

        return this.jdbcTemplate.queryForObject(sql, rowMapper, regDate);
    }

    public List<Post> findPostLikeTitle(String querry){
        String sql = "SELECT * FROM post WHERE title LIKE ?";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper, '%'+querry+'%');
    }

    public List<Post> findPostLikeContent(String querry){
        String sql = "SELECT * FROM post WHERE content LIKE ?";
        RowMapper<Post> rowMapper = new PostMapper();
        return this.jdbcTemplate.query(sql, rowMapper, '%'+querry+'%');
    }

    public int savePost(Post post){
        String sql = "INSERT INTO post(user, title, content, reg_date, updt_date) VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, post.getUser(), post.getTitle(), post.getContent(), post.getRegDate(), post.getUpdtDate());
    }
}
