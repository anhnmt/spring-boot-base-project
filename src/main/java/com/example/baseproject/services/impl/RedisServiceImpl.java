package com.example.baseproject.services.impl;

import com.example.baseproject.common.helpers.BloomFilterHelper;
import com.example.baseproject.common.mappers.JsonMapper;
import com.example.baseproject.common.utils.DataUtils;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    private final BloomFilterHelper<String> bloomFilterHelper;
    private ValueOperations<String, String> valueOps;

    @PostConstruct
    private void init() {
        valueOps = redisTemplate.opsForValue();
    }

    @Override
    public void put(String key, String value) {
        log.info("---> putting {} to redis...........", key);
        valueOps.set(key, value);
        log.info("---> done for put {} to redis...........", key);
    }

    @Override
    public String getData(String key) {
        String value = valueOps.get(key);
        if (Strings.isNotEmpty(value))
            return value;

        return null;
    }

    @Override
    public <T> T getValue(String key, Class<T> classOutput) throws IOException {
        if (Strings.isEmpty(key))
            return null;

        String value = valueOps.get(key);

        if (Strings.isEmpty(value))
            return null;

        return DataUtils.jsonToObject(value, classOutput);
    }

    @Override
    public List<String> getValue(String key) {
        String value = valueOps.get(key);
        return value != null ? JsonMapper.fromJson(value, List.class) : null;
    }

    @Override
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Add value according to the given Bloom filter
     */
    @Override
    public void setBloomFilter(String key, Object value) {
        int[] offset = getOffsets(value);

        Arrays.stream(offset).forEach(i -> {
            System.out.println("key : " + key + " " + "value : " + i);
            valueOps.setBit(key, i, true);
        });
    }

    /**
     * Determine whether the value exists according to the given Bloom filter
     */
    @Override
    public boolean checkBloomFilter(String key, Object value) {
        int[] offset = getOffsets(value);

        return Arrays.stream(offset).allMatch(i -> {
            System.out.println("key : " + key + " " + "value : " + i);
            return valueOps.getBit(key, i);
        });
    }

    @Override
    public void delBloomFilter(String key, Object value) {
        if (checkBloomFilter(key, value))
            del(key);
    }

    private int[] getOffsets(Object value) {
        return bloomFilterHelper.murmurHashOffset(String.valueOf(value));
    }
}
