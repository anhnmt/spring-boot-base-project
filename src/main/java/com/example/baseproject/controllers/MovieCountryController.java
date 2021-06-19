package com.example.baseproject.controllers;

import com.example.baseproject.services.MovieCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieCountryController {
    private final MovieCountryService movieCountryService;

    @GetMapping("/{movieId}/countries")
    public ResponseEntity<Object> findAllByMovieId(@PathVariable Long movieId) {
        return movieCountryService.findAllByMovieId(movieId);
    }
}
