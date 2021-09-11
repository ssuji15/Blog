package com.sujindar.Blog.Service;

import com.sujindar.Blog.Dao.UserDao;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.UserNotFoundException;
import com.sujindar.Blog.Service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserDao mockUserDao;

    @InjectMocks
    private UserServiceImpl userService;

    private static User user;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setFirstName("Sujindar");
        user.setLastName("Selvaraj");
        user.setBlogs(new HashSet<>());
        user.setId(1);
    }


    @Test
    public void testCreateUser() {
        Mockito.when(mockUserDao.save(user)).thenReturn(user);
        Assertions.assertEquals(user,userService.createUser(user));
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        Mockito.when(mockUserDao.findById(1)).thenReturn(Optional.of(user));
        Assertions.assertEquals(user,userService.getUserById(1));
    }
    @Test
    public void testGetUserByIdThrowsError() {
        Mockito.when(mockUserDao.findById(1)).thenReturn(Optional.empty());
        try {
            User user1 = userService.getUserById(1);
        }
        catch(Exception e) {
            Assertions.assertEquals(UserNotFoundException.class,e.getClass());
        }
    }
    @Test
    public void testUpdateUserById() throws UserNotFoundException {
        Mockito.when(mockUserDao.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(mockUserDao.save(user)).thenReturn(user);
        User updatedUser = new User();
        updatedUser.setFirstName("Sujindar");
        updatedUser.setLastName("Selvaraj");
        updatedUser.setBlogs(new HashSet<>());
        updatedUser.setId(1);
        Assertions.assertEquals(updatedUser,userService.updateUserById(1,updatedUser));
    }

    @Test
    public void testUpdateUserByIdThrowsError() {
        Mockito.when(mockUserDao.findById(1)).thenReturn(Optional.empty());
        Mockito.when(mockUserDao.save(user)).thenReturn(user);
        try {
            User updatedUser = userService.updateUserById(1,user);
        }
        catch (Exception e) {
            Assertions.assertEquals(UserNotFoundException.class,e.getClass());
        }
    }

}
