package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.MovieCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCountryRepository extends JpaRepository<MovieCountry, Long> {
}
