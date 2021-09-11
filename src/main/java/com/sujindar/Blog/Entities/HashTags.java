package com.sujindar.Blog.Entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class HashTags {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @Column(unique = true, length = 50,nullable = false)
    private String name;
    @ManyToMany(mappedBy = "hashTags")
    private Set<Blog> blogs = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime createdTime;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof HashTags)) return false;
        HashTags hashTag = (HashTags)obj;
        return this.getId() == hashTag.getId()
                && this.getName() == hashTag.getName()
                && this.getCreatedTime() == hashTag.getCreatedTime();
    }
}
