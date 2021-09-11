package com.sujindar.Blog.Util;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String error;
    private HttpStatus status;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
