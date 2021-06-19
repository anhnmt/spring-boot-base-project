package com.example.baseproject.repositories;

import com.example.baseproject.domains.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserIdAndStatus(Long userId, Long status);
}
