package com.example.baseproject.services;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.ProfileRequest;
import org.springframework.http.ResponseEntity;

public interface PeopleService {
    ResponseEntity<Object> findAll();

    ResponseEntity<Object> findById(Long profileId) throws BaseMessageException;

    ResponseEntity<Object> create(ProfileRequest profileRequest) throws BaseMessageException;

    ResponseEntity<Object> update(Long profileId, ProfileRequest profileRequest) throws BaseMessageException;

    ResponseEntity<Object> delete(Long profileId) throws BaseMessageException;
}
