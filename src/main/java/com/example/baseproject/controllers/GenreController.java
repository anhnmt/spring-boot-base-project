package com.example.baseproject.controllers;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.GenreRequest;
import com.example.baseproject.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<Object> findById(@PathVariable Long genreId) throws BaseMessageException {
        return genreService.findById(genreId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody GenreRequest genreRequest) throws BaseMessageException {
        return genreService.create(genreRequest);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<Object> update(@PathVariable Long genreId, @RequestBody GenreRequest genreRequest) throws BaseMessageException {
        return genreService.update(genreId, genreRequest);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<Object> delete(@PathVariable Long genreId) throws BaseMessageException {
        return genreService.delete(genreId);
    }
}
