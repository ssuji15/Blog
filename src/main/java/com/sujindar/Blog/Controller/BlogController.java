package com.sujindar.Blog.Controller;

import com.sujindar.Blog.Constants.PageSize;
import com.sujindar.Blog.Dto.BlogDto;
import com.sujindar.Blog.Dto.CommentDto;
import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Exception.*;
import com.sujindar.Blog.Service.BlogService;
import com.sujindar.Blog.Util.BlogDtoConverter;
import com.sujindar.Blog.Util.CommentDtoConverter;
import com.sujindar.Blog.Validators.BlogControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity getAllBlogs(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Blog> blogs = blogService.getAllBlog(pageNo==0?0:pageNo-1, PageSize.BLOG_PAGE_SIZE, sortBy);
        List<BlogDto> blogDtos = BlogDtoConverter.convertBlogToBlogDto(blogs);
        return new ResponseEntity(blogDtos, HttpStatus.OK);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity getBlogById(@PathVariable int blogId) throws BlogNotFoundException {
        Blog blog = blogService.getBlogById(blogId);
        BlogDto blogDto = BlogDtoConverter.convertBlogToBlogDto(blog);
        return new ResponseEntity(blogDto,HttpStatus.OK);
    }

    @GetMapping("/hashtag/{hashTagId}")
    public ResponseEntity getAllBlogsByHashTagId(@PathVariable int hashTagId) throws HashTagNotFoundException {
        List<Blog> blogs = blogService.getAllBlogByHashTags(hashTagId);
        List<BlogDto> blogDtos = BlogDtoConverter.convertBlogToBlogDto(blogs);
        return new ResponseEntity(blogDtos,HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity getAllBlogsByUserId(@PathVariable int userId) throws UserNotFoundException {
        List<Blog> blogs = blogService.getAllBlogByUser(userId);
        List<BlogDto> blogDtos = BlogDtoConverter.convertBlogToBlogDto(blogs);
        return new ResponseEntity(blogDtos,HttpStatus.OK);
    }
    @DeleteMapping("/{blogId}")
    public ResponseEntity deleteBlogById(@PathVariable int blogId) throws BlogNotFoundException {
        blogService.deleteBlogById(blogId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createBlog(@RequestBody BlogDto blogDto) throws UserNotFoundException, InvalidBlogDataException {
        BlogControllerValidator.validateCreateBlog(blogDto);
        Blog blog = BlogDtoConverter.convertBlogDtoToBlog(blogDto);
        blog = blogService.createBlog(blog);
        BlogDto responseBlogDto = BlogDtoConverter.convertBlogToBlogDto(blog);
        return new ResponseEntity(responseBlogDto,HttpStatus.CREATED);
    }
    @PutMapping("/{blogId}/addHashTag/{hashTagId}")
    public ResponseEntity addHashTagToaBlog(@PathVariable int blogId,@PathVariable int hashTagId) throws BlogNotFoundException, HashTagNotFoundException {
        Blog blog = blogService.addHashTagToBlog(blogId,hashTagId);
        BlogDto blogDto = BlogDtoConverter.convertBlogToBlogDto(blog);
        return new ResponseEntity(blogDto,HttpStatus.OK);
    }

    @PutMapping("/{blogId}/removeHashTag/{hashTagId}")
    public ResponseEntity removeHashTagFromBlog(@PathVariable int blogId,@PathVariable int hashTagId) throws BlogNotFoundException,HashTagNotFoundException {
        Blog blog = blogService.removeHashTagFromBlog(blogId,hashTagId);
        BlogDto blogDto = BlogDtoConverter.convertBlogToBlogDto(blog);
        return new ResponseEntity(blogDto,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateBlog(@RequestBody BlogDto blogDto) throws BlogNotFoundException, InvalidBlogDataException {
        BlogControllerValidator.validateUpdateBlog(blogDto);
        Blog blog = blogService.updateBlogById(blogDto.getId(),BlogDtoConverter.convertBlogDtoToBlog(blogDto));
        BlogDto responseBlogDto = BlogDtoConverter.convertBlogToBlogDto(blog);
        return new ResponseEntity(responseBlogDto, HttpStatus.OK);
    }
    @PutMapping("/{blogId}/addComment/")
    public ResponseEntity addCommentToBlog(@PathVariable int blogId, @RequestBody CommentDto commentDto) throws BlogNotFoundException, InvalidCommentDataException {
        BlogControllerValidator.ValidateCreateComment(commentDto);
        Comment comment = CommentDtoConverter.convertCommentDtoToComment(commentDto);
        comment = blogService.addCommentToBlog(blogId,comment);
        CommentDto responsecommentDto = CommentDtoConverter.convertCommentToCommentDto(comment);
        return new ResponseEntity(responsecommentDto,HttpStatus.OK);
    }
    @PutMapping("/{blogId}/deleteComment/{commentId}")
    public ResponseEntity deleteCommentFromBlog(@PathVariable int blogId, @PathVariable int commentId) throws BlogNotFoundException, CommentNotFoundException {
        Blog blog = blogService.removeCommentFromBlog(blogId,commentId);
        BlogDto responseBlogDto = BlogDtoConverter.convertBlogToBlogDto(blog);
        return new ResponseEntity(responseBlogDto,HttpStatus.OK);
    }
    @GetMapping("/{blogId}/comments")
    public ResponseEntity getAllCommentsFromBlog(@PathVariable int blogId,
                                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "createdTime") String sortBy) throws BlogNotFoundException {
        List<Comment> comments = blogService.getAllCommentsByBlog(blogId,pageNo==0?0:pageNo-1,PageSize.COMMENT_PAGE_SIZE,sortBy);
        List<CommentDto> commentDtos = CommentDtoConverter.convertCommentToCommentDto(comments);
        return new ResponseEntity(commentDtos,HttpStatus.OK);
    }
}
