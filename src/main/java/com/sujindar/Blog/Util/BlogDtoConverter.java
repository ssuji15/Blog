package com.sujindar.Blog.Util;

import com.sujindar.Blog.Dto.BlogDto;
import com.sujindar.Blog.Dto.HashTagDto;
import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.HashTags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BlogDtoConverter {

    public static BlogDto convertBlogToBlogDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setContent(blog.getContent());
        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());
        blogDto.setCreatedTime(blog.getCreatedTime());
        if(isNotNull(blog.getUser()))
            blogDto.setUser(UserDtoConverter.convertUserToUserDto(blog.getUser()));
        Set<HashTagDto> hashTagDtos = new HashSet<>();
        if(isNotNull(blog.getHashTags())) {
            blog.getHashTags().forEach(hashTags -> {
                hashTagDtos.add(HashTagDtoConverter.convertHashTagsToHashTagsDto(hashTags));
            });
        }
        blogDto.setHashTags(hashTagDtos);
        return blogDto;
    }
    public static List<BlogDto> convertBlogToBlogDto(List<Blog> blogs) {
        List<BlogDto> blogDtos = new ArrayList<>();
        blogs.forEach(blog -> {
            blogDtos.add(convertBlogToBlogDto(blog));
        });
        return blogDtos;
    }

    public static Blog convertBlogDtoToBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setContent(blogDto.getContent());
        blog.setId(blogDto.getId());
        blog.setTitle(blogDto.getTitle());
        if(isNotNull(blogDto.getUser())) {
            blog.setUser(UserDtoConverter.convertUserDtoToUser(blogDto.getUser()));
        }
        Set<HashTags> hashTags = new HashSet<>();
        if(isNotNull(blogDto.getHashTags())) {
            blogDto.getHashTags().forEach(hashTagDto -> {
                hashTags.add(HashTagDtoConverter.convertHashTagDtoToHashTags(hashTagDto));
            });
        }
        blog.setHashTags(hashTags);
        return blog;
    }
    public static boolean isNotNull(Object o) {
        return o != null;
    }

}
