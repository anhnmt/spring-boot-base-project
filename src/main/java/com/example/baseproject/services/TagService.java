package com.example.baseproject.services;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.TagRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface TagService {
    ResponseEntity<Object> findAll();

    ResponseEntity<Object> findById(Long tagId) throws BaseMessageException;

    ResponseEntity<Object> create(TagRequest tagRequest) throws BaseMessageException;

    ResponseEntity<Object> update(Long tagId, TagRequest tagRequest) throws BaseMessageException;

    ResponseEntity<Object> delete(Long tagId) throws BaseMessageException;
}
