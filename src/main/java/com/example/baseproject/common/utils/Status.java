package com.example.baseproject.common.utils;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Arrays;

@AllArgsConstructor
public enum Status {
    DELETED(-1L),
    DRAFT(0L),
    ACTIVE(1L);

    private final Long value;

    public static Status valueOf(int statusCode) throws IllegalArgumentException {
        return resolve(statusCode);
    }

    @Nullable
    public static Status resolve(int statusCode) {
        return Arrays.stream(values()).filter(status -> status.value == statusCode).findFirst().orElse(null);
    }

    public final String getString() {
        return String.valueOf(value);
    }

    public final Long getLong() {
        return value;
    }
}
