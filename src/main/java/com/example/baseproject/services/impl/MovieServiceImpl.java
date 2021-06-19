package com.example.baseproject.services.impl;

import com.example.baseproject.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public ResponseEntity<Object> findAll() {
        return null;
    }
}
