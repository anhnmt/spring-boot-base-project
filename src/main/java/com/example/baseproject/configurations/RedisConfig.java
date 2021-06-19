package com.example.baseproject.configurations;

import com.example.baseproject.common.helpers.BloomFilterHelper;
import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@EnableRedisRepositories
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());

        //Create the Builder for JedisClientConfiguration
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration
                .builder()
                .connectTimeout(redisProperties.getTimeout())
                .readTimeout(redisProperties.getJedis().getPool().getMaxWait());

        if (redisProperties.isSsl()) builder.useSsl();

        //Final JedisClientConfiguration
        JedisClientConfiguration clientConfig = builder.usePooling().build();

        //Create RedisStandAloneConfiguration
        RedisStandaloneConfiguration redisConfig =
                new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());

        //Create JedisConnectionFactory
        return new JedisConnectionFactory(redisConfig, clientConfig);

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }

    //Initialize the Bloom filter and put it into the spring container
    @Bean
    public BloomFilterHelper<String> bloomFilterHelper() {
        return new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8), 10000000, 0.001);
    }
}