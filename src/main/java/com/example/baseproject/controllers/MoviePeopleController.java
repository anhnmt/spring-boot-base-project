package com.example.baseproject.controllers;

import com.example.baseproject.services.MoviePeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MoviePeopleController {
    private final MoviePeopleService moviePeopleService;

    @GetMapping("/{movieId}/people")
    public ResponseEntity<Object> findAllByMovieId(@PathVariable Long movieId) {
        return moviePeopleService.findAllByMovieId(movieId);
    }
}
