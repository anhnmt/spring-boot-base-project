package com.example.baseproject.configuration;

import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.repositories.CountryRepository;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RunnerConfig implements CommandLineRunner {
    private final CountryRepository countryRepository;
    private final RedisService redisService;

    @Override
    public void run(String... args) {
//        countryRepository.findAll().forEach(country -> redisService.setBloomFilter(Constants.REDIS_KEY.BLOOM_LIST_COUNTRY_ID, String.valueOf(country.getCountryId())));
    }
}
