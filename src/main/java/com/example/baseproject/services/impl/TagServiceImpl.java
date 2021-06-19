package com.example.baseproject.services.impl;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.TagMapper;
import com.example.baseproject.common.validators.TagValidator;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.domains.request.TagRequest;
import com.example.baseproject.repositories.TagRepository;
import com.example.baseproject.services.RedisService;
import com.example.baseproject.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.example.baseproject.common.utils.BaseMessage.EMPTY_LIST;
import static com.example.baseproject.common.utils.Status.ACTIVE;
import static com.example.baseproject.common.utils.Status.DELETED;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagValidator tagValidator;
    private final TagMapper tagMapper;
    private final RedisService redisService;

    @Override
    public ResponseEntity<Object> findAll() {
        var tags = tagRepository.findAll();
        if (ObjectUtils.isEmpty(tags))
            return Response.ok(EMPTY_LIST, null);

        return Response.ok(tags);
    }

    @Override
    public ResponseEntity<Object> findById(Long tagId) throws BaseMessageException {
        var tag = tagValidator.validateTagId(tagId);

        return Response.ok(tag);
    }

    @Override
    public ResponseEntity<Object> create(TagRequest tagRequest) throws BaseMessageException {
        var tag = tagMapper.convertToTag(tagRequest);
        tagValidator.validateExistTag(tag);
        tag.setStatus(ACTIVE);
        tagRepository.save(tag);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> update(Long tagId, TagRequest tagRequest) throws BaseMessageException {
        var tag = tagValidator.validateUpdateTag(tagId, tagRequest);
        tagRepository.save(tag);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> delete(Long tagId) throws BaseMessageException {
        var tag = tagValidator.validateTagId(tagId);
        tag.setStatus(DELETED);
        tagRepository.save(tag);

        return Response.ok(tag);
    }
}
