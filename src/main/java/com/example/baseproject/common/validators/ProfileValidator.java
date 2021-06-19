package com.example.baseproject.common.validators;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.ProfileMapper;
import com.example.baseproject.common.utils.BaseMessage;
import com.example.baseproject.common.utils.Constants.REDIS_KEY;
import com.example.baseproject.domains.request.ProfileRequest;
import com.example.baseproject.domains.model.People;
import com.example.baseproject.repositories.ProfileRepository;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProfileValidator {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final RedisService redisService;

    public People validateProfileId(Long profileId) throws BaseMessageException {
        if (ObjectUtils.isEmpty(profileId))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_NOT_EXIST);

        return profileRepository.findById(profileId)
                .orElseThrow(() -> new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_NOT_EXIST));
    }

    public void validateExistProfile(People people) throws BaseMessageException {
        if (ObjectUtils.isEmpty(people))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        if (existsByProfileName(people.getName()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_NAME_EXISTED);

        if (existsByProfileSlug(people.getSlug()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_SLUG_EXISTED);
    }

    public People validateUpdateProfile(Long profileId, ProfileRequest profileRequest) throws BaseMessageException {
        var people = validateProfileId(profileId);

        if (ObjectUtils.isEmpty(profileRequest))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        validateUpdateProfile(people, profileRequest);
        var mapProfile = profileMapper.convertToProfile(people, profileRequest);

        log.info(String.valueOf(mapProfile));
        return mapProfile;
    }

    private void validateUpdateProfile(People oldPeople, ProfileRequest newProfile) throws BaseMessageException {
        if (validateDuplicateProfile(oldPeople, newProfile))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_DUPLICATE);
        else {
            if (unduplicatedProfileName(oldPeople.getName(), newProfile.getName()))
                if (existsByProfileName(newProfile.getName()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_NAME_EXISTED);

            if (unduplicatedProfileSlug(oldPeople.getSlug(), newProfile.getSlug()))
                if (existsByProfileSlug(newProfile.getSlug()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.PROFILE_SLUG_EXISTED);
        }
    }

    private boolean existsByProfileId(Long profileId) {
        if (!redisService.checkBloomFilter(REDIS_KEY.BLOOM_LIST_PROFILE_ID, String.valueOf(profileId)))
            return false;

        return profileRepository.existsById(profileId);
    }

    private boolean notExistsByProfileId(Long profileId) {
        return !existsByProfileId(profileId);
    }

    private boolean existsByProfileName(String profileName) {
        return profileRepository.existsByName(profileName);
    }

    private boolean existsByProfileSlug(String profileSlug) {
        return profileRepository.existsBySlug(profileSlug);
    }

    private boolean validateDuplicateProfile(People oldPeople, ProfileRequest newProfile) {
        return duplicatedProfileName(oldPeople.getName(), newProfile.getName()) && duplicatedProfileSlug(oldPeople.getSlug(), newProfile.getSlug());
    }

    private boolean validateUnduplicatedProfile(People oldPeople, People newPeople) {
        return unduplicatedProfileName(oldPeople.getName(), newPeople.getName()) || unduplicatedProfileSlug(oldPeople.getSlug(), newPeople.getSlug());
    }

    private boolean duplicatedProfileName(String oldName, String newName) {
        return oldName.equals(newName);
    }

    private boolean duplicatedProfileSlug(String oldSlug, String newSlug) {
        return oldSlug.equals(newSlug);
    }

    private boolean unduplicatedProfileName(String oldName, String newName) {
        return !duplicatedProfileName(oldName, newName);
    }

    private boolean unduplicatedProfileSlug(String oldSlug, String newSlug) {
        return !duplicatedProfileSlug(oldSlug, newSlug);
    }
}
