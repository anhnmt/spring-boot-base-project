package com.example.baseproject.services.impl;

import com.example.baseproject.common.mappers.CountryMapper;
import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.common.validators.CountryValidator;
import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.CountryRequest;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.repositories.CountryRepository;
import com.example.baseproject.services.CountryService;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.example.baseproject.common.utils.BaseMessage.EMPTY_LIST;
import static com.example.baseproject.common.utils.Status.ACTIVE;
import static com.example.baseproject.common.utils.Status.DELETED;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryValidator countryValidator;
    private final CountryMapper countryMapper;
    private final RedisService redisService;

    @Override
    public ResponseEntity<Object> findAll() {
        var countries = countryRepository.findAll();
        if (ObjectUtils.isEmpty(countries))
            return Response.ok(EMPTY_LIST, null);

        return Response.ok(countries);
    }

    @Override
    public ResponseEntity<Object> findById(Long countryId) throws BaseMessageException {
        var country = countryValidator.validateCountryId(countryId);

        return Response.ok(country);
    }

    @Override
    public ResponseEntity<Object> create(CountryRequest countryRequest) throws BaseMessageException {
        var country = countryMapper.convertToCountry(countryRequest);
        countryValidator.validateExistCountry(country);
        country.setStatus(ACTIVE);
        countryRepository.save(country);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> update(Long countryId, CountryRequest countryRequest) throws BaseMessageException {
        var country = countryValidator.validateUpdateCountry(countryId, countryRequest);
        countryRepository.save(country);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> delete(Long countryId) throws BaseMessageException {
        var country = countryValidator.validateCountryId(countryId);
        country.setStatus(DELETED);
        countryRepository.save(country);
        redisService.delBloomFilter(Constants.REDIS_KEY.BLOOM_LIST_COUNTRY_ID, String.valueOf(country.getCountryId()));

        return Response.ok(country);
    }
}
