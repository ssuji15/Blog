package com.sujindar.Blog.Service;

import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.CommentNotFoundException;

import java.util.List;

public interface CommentService {

    public Comment createComment(Comment comment);
    public Comment getCommentById(int id) throws CommentNotFoundException;
    public List<Comment> getAllCommentByBlog(Blog blog,int pageNo,int pageSize,String sortBy);
    public List<Comment> getAllCommentByUser(User user,int pageNo,int pageSize,String sortBy);
    public boolean deleteCommentById(int id) throws CommentNotFoundException;
    public Comment updateCommentById(int id,Comment comment) throws CommentNotFoundException;
}
