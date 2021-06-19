package com.example.baseproject.services;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.GenreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface GenreService {
    ResponseEntity<Object> findAll();

    ResponseEntity<Object> findById(Long genreId) throws BaseMessageException;

    ResponseEntity<Object> create(GenreRequest genreRequest) throws BaseMessageException;

    ResponseEntity<Object> update(Long genreId, GenreRequest genreRequest) throws BaseMessageException;

    ResponseEntity<Object> delete(Long genreId) throws BaseMessageException;
}
