package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value = "SELECT a FROM Country a WHERE a.countryId IN (SELECT b.countryId FROM MovieCountry b WHERE b.movieId = ?1)")
    List<Country> findAllByMovieId(Long movieId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    Country findByCountryId(Long countryId);
}
