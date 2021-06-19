package com.example.baseproject.common.mappers;

import com.example.baseproject.domains.model.Country;
import com.example.baseproject.domains.request.CountryRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CountryMapper {
    private final ModelMapper modelMapper;

    public Country convertToCountry(Long countryId, CountryRequest countryRequest) {
        return Country.builder()
                .countryId(countryId)
                .name(countryRequest.getName())
                .slug(countryRequest.getSlug())
                .build();
    }

    public Country convertToCountry(Country country, CountryRequest countryRequest) {
        country.setName(countryRequest.getName());
        country.setSlug(countryRequest.getSlug());

        return country;
    }

    public Country convertToCountry(CountryRequest countryRequest) {
        log.info(String.valueOf(countryRequest));
        return modelMapper.map(countryRequest, Country.class);
    }
}
