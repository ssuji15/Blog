package com.sujindar.Blog.Util;

import com.sujindar.Blog.Dto.CommentDto;
import com.sujindar.Blog.Entities.Comment;

import java.util.ArrayList;
import java.util.List;

public abstract class CommentDtoConverter {

    public static Comment convertCommentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setId(commentDto.getId());
        if(isNotNull(commentDto.getUser()))
            comment.setUser(UserDtoConverter.convertUserDtoToUser(commentDto.getUser()));
        if(isNotNull(commentDto.getBlogDto()))
            comment.setBlog(BlogDtoConverter.convertBlogDtoToBlog(commentDto.getBlogDto()));
        return comment;
    }

    public static CommentDto convertCommentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent(comment.getContent());
        commentDto.setId(comment.getId());
        commentDto.setCreatedTime(comment.getCreatedTime());
        if(isNotNull(comment.getBlog()))
            commentDto.setBlogDto(BlogDtoConverter.convertBlogToBlogDto(comment.getBlog()));
        if(isNotNull(comment.getUser()))
            commentDto.setUser(UserDtoConverter.convertUserToUserDto(comment.getUser()));
        return commentDto;
    }
    public static List<CommentDto> convertCommentToCommentDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        comments.forEach(comment -> {
            commentDtos.add(convertCommentToCommentDto(comment));
        });
        return commentDtos;
    }
    public static boolean isNotNull(Object o) {
        return o != null;
    }
}
