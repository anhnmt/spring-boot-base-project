package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<People, Long> {
    @Query(value = "SELECT a FROM People a WHERE a.profileId IN (SELECT b.profileId FROM MoviePeople b WHERE b.movieId = ?1)")
    List<People> findAllByMovieId(Long movieId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    People findByProfileId(Long profileId);
}
