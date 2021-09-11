package com.sujindar.Blog.Service;

import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public User getUserById(int id) throws UserNotFoundException;
    public User updateUserById(int id,User user) throws UserNotFoundException;;
    public List<Comment> getAllComment(int userID, int pageNo, int pageSize, String sortBy) throws UserNotFoundException;

}
