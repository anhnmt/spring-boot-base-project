package com.example.baseproject.common.exceptions;

import com.example.baseproject.common.utils.BaseMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseMessageException extends Exception {

    private HttpStatus httpStatus;
    private BaseMessage baseMessage;

    public BaseMessageException(HttpStatus httpStatus, BaseMessage baseMessage) {
        super(baseMessage.getMessage());
        this.baseMessage = baseMessage;
        this.httpStatus = httpStatus;
    }
    
}
