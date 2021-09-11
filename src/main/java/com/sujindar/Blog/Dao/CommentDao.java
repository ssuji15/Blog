package com.sujindar.Blog.Dao;

import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByBlog(Blog blog, Pageable pageable);
    List<Comment> findAllByUser(User user, Pageable pageable);
}
