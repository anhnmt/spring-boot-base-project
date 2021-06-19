package com.example.baseproject.services;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface RedisService {
    void put(String key, String value);

    String getData(String key);

    <T> T getValue(String key, Class<T> classOutput) throws IOException;

    List<String> getValue(String key);

    Long del(List<String> keys);

    void del(String key);

    void setBloomFilter(String key, Object value);

    boolean checkBloomFilter(String key, Object value);

    void delBloomFilter(String key, Object value);
}
