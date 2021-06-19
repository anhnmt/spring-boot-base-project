package com.example.baseproject.controllers;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.exceptions.HttpStatusException;
import com.example.baseproject.domains.response.BaseResponse;
import com.example.baseproject.domains.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.baseproject.common.utils.BaseMessage.SYSTEM_ERROR;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        logger.info("[ERROR] >>>> " + ex.getMessage(), ex);
        // quá trình kiểm soát lỗi diễn ra ở đây
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(SYSTEM_ERROR));
    }

    /**
     * HttpStatusException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(HttpStatusException.class)
    public final ResponseEntity<Object> handleHttpStatusException(HttpStatusException ex, WebRequest request) {
        if (Objects.nonNull(ex.getMessage()))
            return ResponseEntity.status(ex.getStatus()).body(new BaseResponse<>(ex.getStatus(), ex.getMessage()));

        return ResponseEntity.status(ex.getStatus()).body(new BaseResponse<>(ex.getStatus()));
    }

    /**
     * BaseMessageException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(BaseMessageException.class)
    public final ResponseEntity<Object> handleBaseMessageException(BaseMessageException ex, WebRequest request) {
        logger.info("[ERROR] >>>> " + ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(new BaseResponse<>(ex.getBaseMessage()));
    }

    /**
     * IllegalArgumentException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleBaseMessageException(IllegalArgumentException ex, WebRequest request) {
        logger.info("[ERROR] >>>> " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    /**
     * NoHandlerFoundException sẽ được xử lý riêng tại đây
     */
    @Override
    public final ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("[ERROR] >>>> " + ex.getMessage(), ex);
        return Response.error(HttpStatus.NOT_FOUND);
    }

    /**
     * Xử lý lỗi HttpRequestMethodNotSupportedException
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    public final ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("[ERROR] >>>> " + ex.getMessage(), ex);
        return Response.error(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("[ERROR] >>>> " + ex.getMessage(), ex);
        List<String> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> details.add(error.getDefaultMessage()));

        return Response.badRequest(10100, "Xác thực không thành công", details);
    }
}
