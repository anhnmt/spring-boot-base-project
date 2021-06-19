package com.example.baseproject.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MovieGenreService {
    ResponseEntity<Object> findAllByMovieId(Long movieId);
}
