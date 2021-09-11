package com.sujindar.Blog.Validators;

import com.sujindar.Blog.Dto.BlogDto;
import com.sujindar.Blog.Dto.CommentDto;
import com.sujindar.Blog.Exception.InvalidBlogDataException;
import com.sujindar.Blog.Exception.InvalidCommentDataException;

public abstract class BlogControllerValidator {

    public static void validateCreateBlog(BlogDto blogDto) throws InvalidBlogDataException {
        if(blogDto.getContent() == null) throw new InvalidBlogDataException();
        if(blogDto.getTitle() == null) throw new InvalidBlogDataException();
        if(blogDto.getUser() == null) throw new InvalidBlogDataException();
    }

    public static void validateUpdateBlog(BlogDto blogDto) throws InvalidBlogDataException {
        if(blogDto.getId() == null) throw new InvalidBlogDataException();
    }

    public static void ValidateCreateComment(CommentDto commentDto) throws InvalidCommentDataException {
        if(commentDto.getContent() == null) throw new InvalidCommentDataException();
        if(commentDto.getUser() == null) throw new InvalidCommentDataException();
    }

}
