package com.example.baseproject.common.validators;

import com.example.baseproject.common.utils.BaseMessage;
import com.example.baseproject.domains.response.Response;
import com.example.baseproject.domains.model.Movie;
import com.example.baseproject.repositories.MovieRepository;
import com.example.baseproject.repositories.MovieTagRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MovieValidator {
    private final MovieRepository movieRepository;
    private final MovieTagRepository movieTagRepository;

    public ResponseEntity<Object> validateMovieId(Long movieId) {
        if (ObjectUtils.isEmpty(movieId) || notExistsByMovieId(movieId))
            return Response.badRequest(BaseMessage.MOVIE_NOT_EXIST);

        return Response.ok();
    }

    public ResponseEntity<Object> validateMovieId(Movie movie) {
        if (ObjectUtils.isEmpty(movie) || notExistsByMovieId(movie))
            return Response.badRequest(BaseMessage.MOVIE_NOT_EXIST);

        return Response.ok();
    }

    private boolean existsByMovieId(Long movieId) {
        return movieRepository.existsById(movieId);
    }

    private boolean existsByMovieId(Movie movie) {
        return movieRepository.existsById(movie.getMovieId());
    }

    private boolean notExistsByMovieId(Long movieId) {
        return !existsByMovieId(movieId);
    }

    private boolean notExistsByMovieId(Movie movie) {
        return !existsByMovieId(movie);
    }
}
