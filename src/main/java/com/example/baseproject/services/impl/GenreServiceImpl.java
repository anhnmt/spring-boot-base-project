package com.example.baseproject.services.impl;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.GenreMapper;
import com.example.baseproject.common.validators.GenreValidator;
import com.example.baseproject.domains.request.GenreRequest;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.repositories.GenreRepository;
import com.example.baseproject.services.GenreService;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.example.baseproject.common.utils.BaseMessage.EMPTY_LIST;
import static com.example.baseproject.common.utils.Status.ACTIVE;
import static com.example.baseproject.common.utils.Status.DELETED;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreValidator genreValidator;
    private final GenreMapper genreMapper;
    private final RedisService redisService;

    @Override
    public ResponseEntity<Object> findAll() {
        var genres = genreRepository.findAll();
        if (ObjectUtils.isEmpty(genres))
            return Response.ok(EMPTY_LIST, null);

        return Response.ok(genres);
    }

    @Override
    public ResponseEntity<Object> findById(Long genreId) throws BaseMessageException {
        var genre = genreValidator.validateGenreId(genreId);

        return Response.ok(genre);
    }

    @Override
    public ResponseEntity<Object> create(GenreRequest genreRequest) throws BaseMessageException {
        var genre = genreMapper.convertToGenre(genreRequest);
        genreValidator.validateExistGenre(genre);
        genre.setStatus(ACTIVE);
        genreRepository.save(genre);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> update(Long genreId, GenreRequest genreRequest) throws BaseMessageException {
        var genre = genreValidator.validateUpdateGenre(genreId, genreRequest);
        genreRepository.save(genre);

        return Response.ok();
    }

    @Override
    public ResponseEntity<Object> delete(Long genreId) throws BaseMessageException {
        var genre = genreValidator.validateGenreId(genreId);
        genre.setStatus(DELETED);
        genreRepository.save(genre);

        return Response.ok(genre);
    }
}
