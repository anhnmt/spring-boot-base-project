package com.example.baseproject.services;

import com.example.baseproject.common.exceptions.HttpStatusException;
import com.example.baseproject.common.exceptions.LogicException;
import com.example.baseproject.domains.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ResponseEntity<Object> findAll();

    ResponseEntity<Object> create(UserRequest userRequest) throws LogicException;
}