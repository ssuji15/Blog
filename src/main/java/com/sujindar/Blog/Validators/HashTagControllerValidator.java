package com.sujindar.Blog.Validators;

import com.sujindar.Blog.Dto.HashTagDto;
import com.sujindar.Blog.Exception.InvalidHashTagDataException;
import org.springframework.stereotype.Component;

@Component
public abstract class HashTagControllerValidator {

    public static void validateCreateHashTagDto(HashTagDto hashTagDto) throws InvalidHashTagDataException {
        if(hashTagDto.getName() == null) throw new InvalidHashTagDataException();
    }

    public static void validateUpdateHashTagDto(HashTagDto hashTagDto) throws InvalidHashTagDataException {
        if(hashTagDto.getId() == null) throw new InvalidHashTagDataException();
    }
}
