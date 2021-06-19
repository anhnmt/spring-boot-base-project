package com.example.baseproject.common.exceptions;

import com.example.baseproject.common.utils.DataUtils;
import com.example.baseproject.common.utils.MessageUtils;

import java.text.MessageFormat;

public class LogicException extends Exception {

    private String errorCode;
    private String message;
    private String description;

    public LogicException() {
    }

    public LogicException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public LogicException(String message) {
        this("", message, null);
    }

    public LogicException(String message, Throwable cause) {
        this("", message, cause);
    }

    public LogicException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        if (message != null && !"".equals(message)) {
            this.message = MessageUtils.getMessage(message);
        } else {
            this.message = "";
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        if (DataUtils.nullOrEmpty(errorCode)) {
            return message;
        }
        return MessageFormat.format("{0}:{1}", errorCode, message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (DataUtils.nullOrEmpty(errorCode)) {
            return message;
        }
        return MessageFormat.format("{0}:{1}", errorCode, message);
    }
}
