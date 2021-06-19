package com.example.baseproject.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface MovieService {
    ResponseEntity<Object> findAll();
}
