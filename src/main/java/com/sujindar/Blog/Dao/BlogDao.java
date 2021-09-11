package com.sujindar.Blog.Dao;

import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogDao extends JpaRepository<Blog,Integer> {
    List<Blog> findAllByHashTags(HashTags hashTags);
    List<Blog> findAllByUser(User user);
}
