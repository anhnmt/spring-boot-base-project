package com.example.baseproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("prelogin")
    public ResponseEntity<Object> prelogin() {

        return null;
    }
}
