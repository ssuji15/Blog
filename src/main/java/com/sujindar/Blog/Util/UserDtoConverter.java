package com.sujindar.Blog.Util;

import com.sujindar.Blog.Dto.UserDto;
import com.sujindar.Blog.Entities.User;

public abstract class UserDtoConverter {
    public static User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
    public static UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCreatedTime(user.getCreatedTime());
        return userDto;
    }
}
