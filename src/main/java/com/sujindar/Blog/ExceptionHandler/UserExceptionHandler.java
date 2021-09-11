package com.sujindar.Blog.ExceptionHandler;

import com.sujindar.Blog.Exception.InvalidUserDataException;
import com.sujindar.Blog.Exception.UserNotFoundException;
import com.sujindar.Blog.Util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity UserNotFoundExceptionHandler() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Invalid User Id");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidUserDataException.class)
    public ResponseEntity handleInvalidBlogDataException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Invalid User Data");
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }

}
