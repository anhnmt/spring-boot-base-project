package com.example.baseproject.controllers;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.CountryRequest;
import com.example.baseproject.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Object> findById(@PathVariable Long countryId) throws BaseMessageException {
        return countryService.findById(countryId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CountryRequest countryRequest) throws BaseMessageException {
        return countryService.create(countryRequest);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<Object> update(@PathVariable Long countryId, @RequestBody CountryRequest countryRequest) throws BaseMessageException {
        return countryService.update(countryId, countryRequest);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Object> delete(@PathVariable Long countryId) throws BaseMessageException {
        return countryService.delete(countryId);
    }
}
