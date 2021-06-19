package com.example.baseproject.domains.response;

import com.example.baseproject.common.utils.BaseMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Response mặc định khi trả về
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private int status;

    private String message;

    private T data;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse(BaseMessage baseMessage, T data) {
        this.status = baseMessage.getStatus();
        this.message = baseMessage.getMessage();
        this.data = data;
    }

    public BaseResponse(BaseMessage baseMessage) {
        this.status = baseMessage.getStatus();
        this.message = baseMessage.getMessage();
    }

    public BaseResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public BaseResponse(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message = message;
    }
}

