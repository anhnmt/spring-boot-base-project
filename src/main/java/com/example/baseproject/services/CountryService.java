package com.example.baseproject.services;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.CountryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CountryService {
    ResponseEntity<Object> findAll();

    ResponseEntity<Object> findById(Long countryId) throws BaseMessageException;

    ResponseEntity<Object> create(CountryRequest countryRequest) throws BaseMessageException;

    ResponseEntity<Object> update(Long countryId, CountryRequest countryRequest) throws BaseMessageException;

    ResponseEntity<Object> delete(Long countryId) throws BaseMessageException;
}
