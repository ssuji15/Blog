package com.sujindar.Blog.Dao;

import com.sujindar.Blog.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {


}
