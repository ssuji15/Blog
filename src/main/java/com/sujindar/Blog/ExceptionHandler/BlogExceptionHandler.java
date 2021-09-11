package com.sujindar.Blog.ExceptionHandler;

import com.sujindar.Blog.Exception.BlogNotFoundException;
import com.sujindar.Blog.Exception.InvalidBlogDataException;
import com.sujindar.Blog.Util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogExceptionHandler {
    @ExceptionHandler(value = BlogNotFoundException.class)
    public ResponseEntity handleBlogNotFoundException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Invalid Blog id");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = InvalidBlogDataException.class)
    public ResponseEntity handleInvalidBlogDataException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Invalid Blog Data");
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }
}
