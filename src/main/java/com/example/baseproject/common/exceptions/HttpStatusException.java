package com.example.baseproject.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpStatusException extends Exception {
    private HttpStatus status;
    private String message;

    protected HttpStatusException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
