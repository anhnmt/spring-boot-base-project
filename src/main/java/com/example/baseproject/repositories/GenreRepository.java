package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.Country;
import com.example.baseproject.domains.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "SELECT a FROM Genre a WHERE a.genreId IN (SELECT b.genreId FROM MovieGenre b WHERE b.movieId = ?1)")
    List<Genre> findAllByMovieId(Long movieId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    Genre findByGenreId(Long genreId);
}
