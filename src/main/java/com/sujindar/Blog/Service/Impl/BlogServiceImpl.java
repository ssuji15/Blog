package com.sujindar.Blog.Service.Impl;

import com.sujindar.Blog.Dao.BlogDao;
import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.BlogNotFoundException;
import com.sujindar.Blog.Exception.CommentNotFoundException;
import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Exception.UserNotFoundException;
import com.sujindar.Blog.Service.BlogService;
import com.sujindar.Blog.Service.HashTagsService;
import com.sujindar.Blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private HashTagsService hashTagsService;

    @Override
    public Blog createBlog(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public boolean deleteBlogById(int blog_id) throws BlogNotFoundException {
        Blog blog = getBlogById(blog_id);
        blogDao.deleteById(blog_id);
        return true;
    }

    @Override
    public Blog updateBlogById(int blog_id, Blog blog) throws BlogNotFoundException {
        Blog updateBlog = getBlogById(blog_id);
        updateBlog.setContent(blog.getContent());
        updateBlog.setTitle(blog.getTitle());
        return blogDao.save(updateBlog);
    }

    @Override
    public Blog getBlogById(int blog_id) throws BlogNotFoundException {
        return blogDao.findById(blog_id).orElseThrow(() -> new BlogNotFoundException());
    }

    @Override
    public List<Blog> getAllBlog(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy).ascending());
        Page<Blog> pagedResult = blogDao.findAll(pageable);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        return new ArrayList<Blog>();
    }

    @Override
    public List<Blog> getAllBlogByHashTags(Integer hashTagId) throws HashTagNotFoundException {
        HashTags hashTags = hashTagsService.getHashTagById(hashTagId);
        return blogDao.findAllByHashTags(hashTags);
    }

    @Override
    public List<Blog> getAllBlogByUser(Integer userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        return blogDao.findAllByUser(user);
    }
    @Override
    public Blog addHashTagToBlog(int blog_id, Integer hashTagId) throws BlogNotFoundException, HashTagNotFoundException {
        HashTags hashTags = hashTagsService.getHashTagById(hashTagId);
        Blog blog = getBlogById(blog_id);
        blog.getHashTags().add(hashTags);
        return blogDao.save(blog);
    }

    @Override
    public Blog removeHashTagFromBlog(int blog_id, Integer hashTagId) throws BlogNotFoundException, HashTagNotFoundException {
        HashTags hashTags = hashTagsService.getHashTagById(hashTagId);
        Blog blog = getBlogById(blog_id);
        if(!blog.getHashTags().contains(hashTags)) throw new HashTagNotFoundException();
        blog.getHashTags().remove(hashTags);
        return blogDao.save(blog);
    }

    @Override
    public Comment addCommentToBlog(int blogId, Comment comment) throws BlogNotFoundException {
        Blog blog = getBlogById(blogId);
        comment.setBlog(blog);
        comment.setUser(blog.getUser());
        return commentService.createComment(comment);
    }

    @Override
    public Blog removeCommentFromBlog(int blogId, int commentId) throws BlogNotFoundException, CommentNotFoundException {
        commentService.deleteCommentById(commentId);
        return getBlogById(blogId);
    }

    @Override
    public List<Comment> getAllCommentsByBlog(int blogId,int pageNo,int pageSize,String sortBy) throws BlogNotFoundException {
        return commentService.getAllCommentByBlog(getBlogById(blogId),pageNo,pageSize,sortBy);
    }
}
