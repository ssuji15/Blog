package com.sujindar.Blog.ExceptionHandler;

import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Exception.InvalidHashTagDataException;
import com.sujindar.Blog.Util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class HashTagExceptionHandler {
    @ExceptionHandler(value = HashTagNotFoundException.class)
    public ResponseEntity handleHashTagNotFoundException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Invalid HashTag id");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleIntegrityConstraint() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("HashTag Already Exist");
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidHashTagDataException.class)
    public ResponseEntity handleInvalidBlogDataException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Invalid HashTag Data");
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }
}
