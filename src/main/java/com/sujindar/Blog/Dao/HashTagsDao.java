package com.sujindar.Blog.Dao;

import com.sujindar.Blog.Entities.HashTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagsDao extends JpaRepository<HashTags,Integer> {
}
