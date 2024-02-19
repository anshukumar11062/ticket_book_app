package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.User;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Status - Closed
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmailAndPassword(String email, String password);

    public User findByEmail(String email);
}
