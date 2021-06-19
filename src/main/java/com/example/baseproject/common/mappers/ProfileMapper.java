package com.example.baseproject.common.mappers;

import com.example.baseproject.domains.request.ProfileRequest;
import com.example.baseproject.domains.model.People;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ProfileMapper {
    private final ModelMapper modelMapper;

    public People convertToProfile(Long profileId, ProfileRequest profileRequest) {
        return People.builder()
                .profileId(profileId)
                .name(profileRequest.getName())
                .slug(profileRequest.getSlug())
                .build();
    }

    public People convertToProfile(People country, ProfileRequest profileRequest) {
        country.setName(profileRequest.getName());
        country.setSlug(profileRequest.getSlug());

        return country;
    }

    public People convertToProfile(ProfileRequest profileRequest) {
        log.info(String.valueOf(profileRequest));
        return modelMapper.map(profileRequest, People.class);
    }
}
