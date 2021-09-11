package com.sujindar.Blog.Validators;

import com.sujindar.Blog.Dto.UserDto;
import com.sujindar.Blog.Exception.InvalidUserDataException;


public abstract class UserControllerValidator {

    public static void validateCreateUserDto(UserDto userDto) throws InvalidUserDataException {
        if(userDto.getLastName() == null) throw new InvalidUserDataException();
        if(userDto.getFirstName() == null) throw new InvalidUserDataException();
    }

    public static void validateUpdateUserDto(UserDto userDto) throws InvalidUserDataException {
        if(userDto.getId() == null) throw new InvalidUserDataException();
    }
}
