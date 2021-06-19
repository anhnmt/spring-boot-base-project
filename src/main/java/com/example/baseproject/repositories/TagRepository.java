package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT a FROM Tag a WHERE a.tagId IN (SELECT b.tagId FROM MovieTag b WHERE b.movieId = ?1)")
    List<Tag> findAllByMovieId(Long movieId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    Tag findByTagId(Long tagId);
}
