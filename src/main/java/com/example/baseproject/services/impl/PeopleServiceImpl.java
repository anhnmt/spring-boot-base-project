package com.example.baseproject.services.impl;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.ProfileMapper;
import com.example.baseproject.common.validators.ProfileValidator;
import com.example.baseproject.domains.request.ProfileRequest;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.repositories.ProfileRepository;
import com.example.baseproject.services.PeopleService;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.example.baseproject.common.utils.BaseMessage.EMPTY_LIST;
import static com.example.baseproject.common.utils.Status.ACTIVE;
import static com.example.baseproject.common.utils.Status.DELETED;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {
    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;
    private final ProfileMapper profileMapper;
    private final RedisService redisService;

    @Override
    public ResponseEntity<Object> findAll() {
        var profiles = profileRepository.findAll();
        if (ObjectUtils.isEmpty(profiles))
            return Response.ok(EMPTY_LIST, null);

        return Response.ok(profiles);
    }

    @Override
    public ResponseEntity<Object> findById(Long profileId) throws BaseMessageException {
        var people = profileValidator.validateProfileId(profileId);

        return Response.ok(people);
    }

    @Override
    public ResponseEntity<Object> create(ProfileRequest profileRequest) throws BaseMessageException {
        var people = profileMapper.convertToProfile(profileRequest);
        profileValidator.validateExistProfile(people);
        people.setStatus(ACTIVE);
        profileRepository.save(people);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> update(Long profileId, ProfileRequest profileRequest) throws BaseMessageException {
        var people = profileValidator.validateUpdateProfile(profileId, profileRequest);
        profileRepository.save(people);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> delete(Long profileId) throws BaseMessageException {
        var people = profileValidator.validateProfileId(profileId);
        people.setStatus(DELETED);
        profileRepository.save(people);

        return Response.ok(people);
    }
}
