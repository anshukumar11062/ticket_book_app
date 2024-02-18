package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.MovieMaster;

@Repository
public interface MovieMasterRepository extends JpaRepository<MovieMaster, Long> {

}
