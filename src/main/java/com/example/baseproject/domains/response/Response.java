package com.example.baseproject.domains.response;

import com.example.baseproject.common.utils.BaseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * DTO trả về thông tin lỗi
 */
public class Response {

    public static ResponseEntity<Object> internalServerError(String msg) {
        return ResponseEntity
                .status(500)
                .body(new BaseResponse<>(500, msg));
    }

//    public static ResponseEntity<?> httpError(HttpErrorException e) {
//        return ResponseEntity
//                .status(e.getStatus())
//                .body(new SystemResponse<>(500, e.getMessage()));
//    }

    public static ResponseEntity<Object> unauthorized(String msg) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new BaseResponse<>(401, msg));
    }

    public static ResponseEntity<Object> badRequest(String msg) {
        return ResponseEntity
                .badRequest()
                .body(new BaseResponse<>(400, msg));
    }

    public static ResponseEntity<Object> badRequest(BaseMessage baseMessage) {
        return ResponseEntity
                .badRequest()
                .body(new BaseResponse<>(baseMessage));
    }

    public static ResponseEntity<Object> badRequest(int code, String msg) {
        return ResponseEntity
                .badRequest()
                .body(new BaseResponse<>(code, msg));
    }

    public static ResponseEntity<Object> badRequest(int code, String msg, Object data) {
        return ResponseEntity
                .badRequest()
                .body(new BaseResponse<>(code, msg, data));
    }

//    public static ResponseEntity<SystemResponse<?>> badRequest(int code) {
//        return ResponseEntity
//                .badRequest()
//                .body(new SystemResponse<>(code, Message.getError(code), null));
//    }


    public static ResponseEntity<Object> forbidden(int code, String msg) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new BaseResponse<>(code, msg));
    }

    public static ResponseEntity<Object> failDependency(int code, String msg) {
        return ResponseEntity
                .status(424)
                .body(new BaseResponse<>(code, msg));
    }

    public static ResponseEntity<Object> ok(int code, String msg, Object data) {
        return ResponseEntity.ok(new BaseResponse<>(code, msg, data));
    }

//    public static <T> ResponseEntity<SystemResponse<T>> ok(int code, T data) {
//        return ResponseEntity.ok(new SystemResponse<T>(code, Message.OK, data));
//    }

    public static ResponseEntity<Object> ok(BaseMessage baseMessage) {
        return ResponseEntity.ok(new BaseResponse<>(baseMessage));
    }

    public static ResponseEntity<Object> ok(BaseMessage baseMessage, Object data) {
        return ResponseEntity.ok(new BaseResponse<>(baseMessage, data));
    }

    public static ResponseEntity<Object> ok(Object data) {
        return ResponseEntity.ok(new BaseResponse<>(BaseMessage.OK, data));
    }

    public static ResponseEntity<Object> ok(int code, String msg) {
        return ResponseEntity.ok(new BaseResponse<>(code, msg, null));
    }

//    public static ResponseEntity<SystemResponse<?>> ok(int code) {
//        return ResponseEntity.ok(new SystemResponse<>(code, StringResponse.getError(code), null));
//    }

    public static ResponseEntity<Object> ok(String msg) {
        return ResponseEntity.ok(new BaseResponse<>(200, msg, null));
    }

    public static ResponseEntity<Object> ok() {
        return ResponseEntity.ok(new BaseResponse<>(BaseMessage.OK));
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(new BaseResponse<>(httpStatus));
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, BaseMessage baseMessage) {
        return ResponseEntity
                .status(httpStatus)
                .body(new BaseResponse<>(baseMessage));
    }
}

