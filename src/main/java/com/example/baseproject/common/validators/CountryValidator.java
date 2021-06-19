package com.example.baseproject.common.validators;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.CountryMapper;
import com.example.baseproject.common.utils.BaseMessage;
import com.example.baseproject.common.utils.Constants.REDIS_KEY;
import com.example.baseproject.domains.request.CountryRequest;
import com.example.baseproject.domains.model.Country;
import com.example.baseproject.repositories.CountryRepository;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CountryValidator {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final RedisService redisService;

    public Country validateCountryId(Long countryId) throws BaseMessageException {
        if (ObjectUtils.isEmpty(countryId))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_NOT_EXIST);

        return countryRepository.findById(countryId)
                .orElseThrow(() -> new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_NOT_EXIST));
    }

    public void validateExistCountry(Country country) throws BaseMessageException {
        if (ObjectUtils.isEmpty(country))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        if (existsByCountryName(country.getName()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_NAME_EXISTED);

        if (existsByCountrySlug(country.getSlug()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_SLUG_EXISTED);
    }

    public Country validateUpdateCountry(Long countryId, CountryRequest countryRequest) throws BaseMessageException {
        var country = validateCountryId(countryId);

        if (ObjectUtils.isEmpty(countryRequest))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        validateUpdateCountry(country, countryRequest);
        var mapCountry = countryMapper.convertToCountry(country, countryRequest);

        log.info(String.valueOf(mapCountry));
        return mapCountry;
    }

    private void validateUpdateCountry(Country oldCountry, CountryRequest newCountry) throws BaseMessageException {
        if (validateDuplicateCountry(oldCountry, newCountry))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_DUPLICATE);
        else {
            if (unduplicatedCountryName(oldCountry.getName(), newCountry.getName()))
                if (existsByCountryName(newCountry.getName()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_NAME_EXISTED);

            if (unduplicatedCountrySlug(oldCountry.getSlug(), newCountry.getSlug()))
                if (existsByCountrySlug(newCountry.getSlug()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.COUNTRY_SLUG_EXISTED);
        }
    }

    private boolean existsByCountryId(Long countryId) {
        if (!redisService.checkBloomFilter(REDIS_KEY.BLOOM_LIST_COUNTRY_ID, String.valueOf(countryId)))
            return false;

        return countryRepository.existsById(countryId);
    }

    private boolean notExistsByCountryId(Long countryId) {
        return !existsByCountryId(countryId);
    }

    private boolean existsByCountryName(String countryName) {
        return countryRepository.existsByName(countryName);
    }

    private boolean existsByCountrySlug(String countrySlug) {
        return countryRepository.existsBySlug(countrySlug);
    }

    private boolean validateDuplicateCountry(Country oldCountry, CountryRequest newCountry) {
        return duplicatedCountryName(oldCountry.getName(), newCountry.getName()) && duplicatedCountrySlug(oldCountry.getSlug(), newCountry.getSlug());
    }

    private boolean validateUnduplicatedCountry(Country oldCountry, Country newCountry) {
        return unduplicatedCountryName(oldCountry.getName(), newCountry.getName()) || unduplicatedCountrySlug(oldCountry.getSlug(), newCountry.getSlug());
    }

    private boolean duplicatedCountryName(String oldName, String newName) {
        return oldName.equals(newName);
    }

    private boolean duplicatedCountrySlug(String oldSlug, String newSlug) {
        return oldSlug.equals(newSlug);
    }

    private boolean unduplicatedCountryName(String oldName, String newName) {
        return !duplicatedCountryName(oldName, newName);
    }

    private boolean unduplicatedCountrySlug(String oldSlug, String newSlug) {
        return !duplicatedCountrySlug(oldSlug, newSlug);
    }
}
