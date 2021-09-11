package com.sujindar.Blog.Service.Impl;

import com.sujindar.Blog.Dao.UserDao;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.UserNotFoundException;
import com.sujindar.Blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentServiceImpl commentService;

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return userDao.findById(id).orElseThrow(()-> new UserNotFoundException());
    }

    @Override
    public User updateUserById(int id, User user) throws UserNotFoundException {
        User user1 = getUserById(id);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        return userDao.save(user1);
    }

    @Override
    public List<Comment> getAllComment(int userID, int pageNo, int pageSize, String sortBy) throws UserNotFoundException {
        User user = getUserById(userID);
        return commentService.getAllCommentByUser(user,pageNo,pageSize,sortBy);
    }


}
