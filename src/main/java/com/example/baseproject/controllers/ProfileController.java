package com.example.baseproject.controllers;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.ProfileRequest;
import com.example.baseproject.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final PeopleService peopleService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return peopleService.findAll();
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Object> findById(@PathVariable Long profileId) throws BaseMessageException {
        return peopleService.findById(profileId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProfileRequest profileRequest) throws BaseMessageException {
        return peopleService.create(profileRequest);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Object> update(@PathVariable Long profileId, @RequestBody ProfileRequest profileRequest) throws BaseMessageException {
        return peopleService.update(profileId, profileRequest);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Object> delete(@PathVariable Long profileId) throws BaseMessageException {
        return peopleService.delete(profileId);
    }
}
