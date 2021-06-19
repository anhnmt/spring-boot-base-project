package com.example.baseproject.controllers;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.domains.request.TagRequest;
import com.example.baseproject.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return tagService.findAll();
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<Object> findById(@PathVariable Long tagId) throws BaseMessageException {
        return tagService.findById(tagId);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody TagRequest tagRequest) throws BaseMessageException {
        return tagService.create(tagRequest);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<Object> update(@PathVariable Long tagId, @RequestBody TagRequest tagRequest) throws BaseMessageException {
        return tagService.update(tagId, tagRequest);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Object> delete(@PathVariable Long tagId) throws BaseMessageException {
        return tagService.delete(tagId);
    }
}
