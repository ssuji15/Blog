package com.sujindar.Blog.Service.Impl;

import com.sujindar.Blog.Dao.HashTagsDao;
import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Service.HashTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HashTagsServiceImpl implements HashTagsService {

    @Autowired
    private HashTagsDao hashTagsDao;

    @Override
    public List<HashTags> getAllHashTag() {
        return hashTagsDao.findAll();
    }

    @Override
    public HashTags getHashTagById(int id) throws HashTagNotFoundException {
        return hashTagsDao.findById(id).orElseThrow(()->new HashTagNotFoundException());
    }

    @Override
    public HashTags createHashTag(HashTags hashTags) {
        return hashTagsDao.save(hashTags);
    }

    @Override
    public boolean deleteHashTagById(int id) throws HashTagNotFoundException {
        HashTags hashTag = getHashTagById(id);
        hashTagsDao.deleteById(id);
        return true;
    }

    @Override
    public HashTags updateHashTagById(int id, HashTags hashTags) throws HashTagNotFoundException {
        HashTags hashTag = getHashTagById(id);
        hashTag.setName(hashTags.getName());
        hashTag.setBlogs(hashTags.getBlogs());
        return hashTagsDao.save(hashTag);
    }
}
