package com.sujindar.Blog.Entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    private Blog blog;
    @ManyToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime createdTime;

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Comment)) return false;
        Comment comment = (Comment) obj;
        return this.getContent() == comment.getContent()
                && this.getCreatedTime() == comment.getCreatedTime()
                && this.getId() == comment.getId();
    }
}
