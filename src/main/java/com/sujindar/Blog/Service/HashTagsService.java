package com.sujindar.Blog.Service;

import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Exception.HashTagNotFoundException;

import java.util.List;

public interface HashTagsService {
    public List<HashTags> getAllHashTag();
    public HashTags getHashTagById(int id) throws HashTagNotFoundException;
    public HashTags createHashTag(HashTags hashTags);
    public boolean deleteHashTagById(int id) throws HashTagNotFoundException;
    public HashTags updateHashTagById(int id,HashTags hashTags) throws HashTagNotFoundException;
}
