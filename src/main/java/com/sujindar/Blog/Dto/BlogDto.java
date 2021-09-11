package com.sujindar.Blog.Dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class BlogDto {

    private Integer id;
    private String title;
    private String content;
    private UserDto user;
    private Set<HashTagDto> hashTags = new HashSet<>();
    private LocalDateTime createdTime;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<HashTagDto> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTagDto> hashTags) {
        this.hashTags = hashTags;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
