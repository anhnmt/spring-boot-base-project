package com.example.baseproject.controllers;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.exceptions.HttpStatusException;
import com.example.baseproject.common.exceptions.LogicException;
import com.example.baseproject.domains.request.UserRequest;
import com.example.baseproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserRequest userRequest) throws LogicException {
        return userService.create(userRequest);
    }
}
