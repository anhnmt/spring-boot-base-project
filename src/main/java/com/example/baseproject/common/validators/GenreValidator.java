package com.example.baseproject.common.validators;

import com.example.baseproject.common.exceptions.BaseMessageException;
import com.example.baseproject.common.mappers.GenreMapper;
import com.example.baseproject.common.utils.BaseMessage;
import com.example.baseproject.common.utils.Constants.REDIS_KEY;
import com.example.baseproject.domains.request.GenreRequest;
import com.example.baseproject.domains.model.Genre;
import com.example.baseproject.repositories.GenreRepository;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class GenreValidator {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final RedisService redisService;

    public Genre validateGenreId(Long genreId) throws BaseMessageException {
        if (ObjectUtils.isEmpty(genreId))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_NOT_EXIST);

        return genreRepository.findById(genreId)
                .orElseThrow(() -> new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_NOT_EXIST));
    }

    public void validateExistGenre(Genre genre) throws BaseMessageException {
        if (ObjectUtils.isEmpty(genre))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        if (existsByGenreName(genre.getName()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_NAME_EXISTED);

        if (existsByGenreSlug(genre.getSlug()))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_SLUG_EXISTED);
    }

    public Genre validateUpdateGenre(Long genreId, GenreRequest genreRequest) throws BaseMessageException {
        var genre = validateGenreId(genreId);

        if (ObjectUtils.isEmpty(genreRequest))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.EMPTY_INPUT);

        validateUpdateGenre(genre, genreRequest);
        var mapGenre = genreMapper.convertToGenre(genre, genreRequest);

        log.info(String.valueOf(mapGenre));
        return mapGenre;
    }

    private void validateUpdateGenre(Genre oldGenre, GenreRequest newGenre) throws BaseMessageException {
        if (validateDuplicateGenre(oldGenre, newGenre))
            throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_DUPLICATE);
        else {
            if (unduplicatedGenreName(oldGenre.getName(), newGenre.getName()))
                if (existsByGenreName(newGenre.getName()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_NAME_EXISTED);

            if (unduplicatedGenreSlug(oldGenre.getSlug(), newGenre.getSlug()))
                if (existsByGenreSlug(newGenre.getSlug()))
                    throw new BaseMessageException(HttpStatus.BAD_REQUEST, BaseMessage.GENRE_SLUG_EXISTED);
        }
    }

    private boolean existsByGenreId(Long genreId) {
        if (!redisService.checkBloomFilter(REDIS_KEY.BLOOM_LIST_GENRE_ID, String.valueOf(genreId)))
            return false;

        return genreRepository.existsById(genreId);
    }

    private boolean notExistsByGenreId(Long genreId) {
        return !existsByGenreId(genreId);
    }

    private boolean existsByGenreName(String genreName) {
        return genreRepository.existsByName(genreName);
    }

    private boolean existsByGenreSlug(String genreSlug) {
        return genreRepository.existsBySlug(genreSlug);
    }

    private boolean validateDuplicateGenre(Genre oldGenre, GenreRequest newGenre) {
        return duplicatedGenreName(oldGenre.getName(), newGenre.getName()) && duplicatedGenreSlug(oldGenre.getSlug(), newGenre.getSlug());
    }

    private boolean validateUnduplicatedGenre(Genre oldGenre, Genre newGenre) {
        return unduplicatedGenreName(oldGenre.getName(), newGenre.getName()) || unduplicatedGenreSlug(oldGenre.getSlug(), newGenre.getSlug());
    }

    private boolean duplicatedGenreName(String oldName, String newName) {
        return oldName.equals(newName);
    }

    private boolean duplicatedGenreSlug(String oldSlug, String newSlug) {
        return oldSlug.equals(newSlug);
    }

    private boolean unduplicatedGenreName(String oldName, String newName) {
        return !duplicatedGenreName(oldName, newName);
    }

    private boolean unduplicatedGenreSlug(String oldSlug, String newSlug) {
        return !duplicatedGenreSlug(oldSlug, newSlug);
    }
}
