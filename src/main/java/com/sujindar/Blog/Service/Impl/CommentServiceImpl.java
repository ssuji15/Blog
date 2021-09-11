package com.sujindar.Blog.Service.Impl;

import com.sujindar.Blog.Dao.CommentDao;
import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.CommentNotFoundException;
import com.sujindar.Blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment createComment(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Comment getCommentById(int id) throws CommentNotFoundException {
        return commentDao.findById(id).orElseThrow(() -> new CommentNotFoundException());
    }

    @Override
    public List<Comment> getAllCommentByBlog(Blog blog,int pageNo,int pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy).ascending());
        return commentDao.findAllByBlog(blog,pageable);
    }

    public List<Comment> getAllCommentByUser(User user, int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy).ascending());
        return commentDao.findAllByUser(user,pageable);
    }

    @Override
    public boolean deleteCommentById(int id) throws CommentNotFoundException {
        Comment comment = getCommentById(id);
        commentDao.deleteById(id);
        return true;
    }

    @Override
    public Comment updateCommentById(int id, Comment comment) throws CommentNotFoundException {
        Comment updateComment = getCommentById(id);
        updateComment.setContent(comment.getContent());
        return commentDao.save(updateComment);
    }



}
