package com.sujindar.Blog.Service;

import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Exception.BlogNotFoundException;
import com.sujindar.Blog.Exception.CommentNotFoundException;
import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Exception.UserNotFoundException;

import java.util.List;

public interface BlogService {
    public Blog createBlog(Blog blog);
    public boolean deleteBlogById(int id)  throws BlogNotFoundException;
    public Blog updateBlogById(int id,Blog blog) throws BlogNotFoundException;
    public Blog getBlogById(int id) throws BlogNotFoundException;
    public List<Blog> getAllBlog(Integer pageNo,Integer pageSize,String sortBy);
    public List<Blog> getAllBlogByHashTags(Integer hashTagId) throws HashTagNotFoundException;
    public List<Blog> getAllBlogByUser(Integer userId) throws UserNotFoundException;
    public Blog addHashTagToBlog(int id,Integer hashTagId) throws BlogNotFoundException, HashTagNotFoundException;
    public Blog removeHashTagFromBlog(int id,Integer hashTagId) throws BlogNotFoundException, HashTagNotFoundException;
    public Comment addCommentToBlog(int blogId, Comment comment) throws  BlogNotFoundException;
    public Blog removeCommentFromBlog(int blogId,int commentId) throws BlogNotFoundException, CommentNotFoundException;
    public List<Comment> getAllCommentsByBlog(int blogId,int pageNo,int pageSize,String sortBy) throws BlogNotFoundException;
}
