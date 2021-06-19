package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.MoviePeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePeopleRepository extends JpaRepository<MoviePeople, Long> {
}
