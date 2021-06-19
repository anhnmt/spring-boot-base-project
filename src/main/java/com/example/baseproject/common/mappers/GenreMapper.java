package com.example.baseproject.common.mappers;

import com.example.baseproject.domains.request.GenreRequest;
import com.example.baseproject.domains.model.Genre;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class GenreMapper {
    private final ModelMapper modelMapper;

    public Genre convertToGenre(Long genreId, GenreRequest genreRequest) {
        return Genre.builder()
                .genreId(genreId)
                .name(genreRequest.getName())
                .slug(genreRequest.getSlug())
                .build();
    }

    public Genre convertToGenre(Genre country, GenreRequest genreRequest) {
        country.setName(genreRequest.getName());
        country.setSlug(genreRequest.getSlug());

        return country;
    }

    public Genre convertToGenre(GenreRequest genreRequest) {
        log.info(String.valueOf(genreRequest));
        return modelMapper.map(genreRequest, Genre.class);
    }
}
