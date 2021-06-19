package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.MovieTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTagRepository extends JpaRepository<MovieTag, Long> {
}
