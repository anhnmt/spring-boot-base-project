package com.example.baseproject.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MovieTagService {
    ResponseEntity<Object> findAllByMovieId(Long movieId);
}
