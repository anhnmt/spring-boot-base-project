package com.example.baseproject.common.validators;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.TagMapper;
import com.example.baseproject.common.utils.BaseMessage;
import com.example.baseproject.common.utils.Constants.REDIS_KEY;
import com.example.baseproject.domains.request.TagRequest;
import com.example.baseproject.domains.model.Tag;
import com.example.baseproject.repositories.TagRepository;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TagValidator {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final RedisService redisService;

    public Tag validateTagId(Long tagId) throws BaseMessageException {
        if (ObjectUtils.isEmpty(tagId))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_NOT_EXIST);

        return tagRepository.findById(tagId)
                .orElseThrow(() -> new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_NOT_EXIST));
    }

    public void validateExistTag(Tag tag) throws BaseMessageException {
        if (ObjectUtils.isEmpty(tag))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        if (existsByTagName(tag.getName()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_NAME_EXISTED);

        if (existsByTagSlug(tag.getSlug()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_SLUG_EXISTED);
    }

    public Tag validateUpdateTag(Long tagId, TagRequest tagRequest) throws BaseMessageException {
        var tag = validateTagId(tagId);

        if (ObjectUtils.isEmpty(tagRequest))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        validateUpdateTag(tag, tagRequest);
        var mapTag = tagMapper.convertToTag(tag, tagRequest);

        log.info(String.valueOf(mapTag));
        return mapTag;
    }

    private void validateUpdateTag(Tag oldTag, TagRequest newTag) throws BaseMessageException {
        if (validateDuplicateTag(oldTag, newTag))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_DUPLICATE);
        else {
            if (unduplicatedTagName(oldTag.getName(), newTag.getName()))
                if (existsByTagName(newTag.getName()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_NAME_EXISTED);

            if (unduplicatedTagSlug(oldTag.getSlug(), newTag.getSlug()))
                if (existsByTagSlug(newTag.getSlug()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.TAG_SLUG_EXISTED);
        }
    }

    private boolean existsByTagId(Long tagId) {
        if (!redisService.checkBloomFilter(REDIS_KEY.BLOOM_LIST_TAG_ID, String.valueOf(tagId)))
            return false;

        return tagRepository.existsById(tagId);
    }

    private boolean notExistsByTagId(Long tagId) {
        return !existsByTagId(tagId);
    }

    private boolean existsByTagName(String tagName) {
        return tagRepository.existsByName(tagName);
    }

    private boolean existsByTagSlug(String tagSlug) {
        return tagRepository.existsBySlug(tagSlug);
    }

    private boolean validateDuplicateTag(Tag oldTag, TagRequest newTag) {
        return duplicatedTagName(oldTag.getName(), newTag.getName()) && duplicatedTagSlug(oldTag.getSlug(), newTag.getSlug());
    }

    private boolean validateUnduplicatedTag(Tag oldTag, Tag newTag) {
        return unduplicatedTagName(oldTag.getName(), newTag.getName()) || unduplicatedTagSlug(oldTag.getSlug(), newTag.getSlug());
    }

    private boolean duplicatedTagName(String oldName, String newName) {
        return oldName.equals(newName);
    }

    private boolean duplicatedTagSlug(String oldSlug, String newSlug) {
        return oldSlug.equals(newSlug);
    }

    private boolean unduplicatedTagName(String oldName, String newName) {
        return !duplicatedTagName(oldName, newName);
    }

    private boolean unduplicatedTagSlug(String oldSlug, String newSlug) {
        return !duplicatedTagSlug(oldSlug, newSlug);
    }
}
