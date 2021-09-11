package com.sujindar.Blog.Controller;

import com.sujindar.Blog.Constants.PageSize;
import com.sujindar.Blog.Dto.CommentDto;
import com.sujindar.Blog.Dto.UserDto;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.InvalidUserDataException;
import com.sujindar.Blog.Exception.UserNotFoundException;
import com.sujindar.Blog.Service.Impl.UserServiceImpl;
import com.sujindar.Blog.Util.CommentDtoConverter;
import com.sujindar.Blog.Util.UserDtoConverter;
import com.sujindar.Blog.Validators.UserControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDto userDto) throws InvalidUserDataException {
        UserControllerValidator.validateCreateUserDto(userDto);
        User user = UserDtoConverter.convertUserDtoToUser(userDto);
        User createdUser = userService.createUser(user);
        UserDto responseUser = UserDtoConverter.convertUserToUserDto(createdUser);
        return new ResponseEntity(responseUser,HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable int userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        UserDto responseUser = UserDtoConverter.convertUserToUserDto(user);
        return new ResponseEntity(responseUser,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity updateUserById(@RequestBody UserDto userDto) throws UserNotFoundException, InvalidUserDataException {
        UserControllerValidator.validateUpdateUserDto(userDto);
        User user = UserDtoConverter.convertUserDtoToUser(userDto);
        User updatedUser = userService.updateUserById(user.getId(),user);
        UserDto responseUser = UserDtoConverter.convertUserToUserDto(updatedUser);
        return new ResponseEntity(responseUser,HttpStatus.OK);
    }

    @GetMapping("{userId}/comments")
    public ResponseEntity getAllCommentByUser(@PathVariable int userId,
                                              @RequestParam(defaultValue = "0") Integer pageNo,
                                              @RequestParam(defaultValue = "createdTime") String sortBy) throws UserNotFoundException {
        List<Comment> comments = userService.getAllComment(userId,pageNo==0?0:pageNo-1, PageSize.COMMENT_PAGE_SIZE,sortBy);
        List<CommentDto> commentDtos = CommentDtoConverter.convertCommentToCommentDto(comments);
        return new ResponseEntity(commentDtos,HttpStatus.OK);
    }

}
