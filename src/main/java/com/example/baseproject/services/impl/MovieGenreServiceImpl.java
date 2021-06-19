package com.example.baseproject.services.impl;

import com.example.baseproject.common.validators.MovieValidator;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.repositories.GenreRepository;
import com.example.baseproject.services.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.example.baseproject.common.utils.BaseMessage.EMPTY_LIST;

@RequiredArgsConstructor
@Service
public class MovieGenreServiceImpl implements MovieGenreService {
    private final GenreRepository genreRepository;
    private final MovieValidator movieValidator;

    @Override
    public ResponseEntity<Object> findAllByMovieId(Long movieId) {
        var validate = movieValidator.validateMovieId(movieId);
        if (!validate.getStatusCode().is2xxSuccessful())
            return validate;

        var genres = genreRepository.findAllByMovieId(movieId);
        if (ObjectUtils.isEmpty(genres))
            return Response.ok(EMPTY_LIST);

        return Response.ok(genres);
    }
}
