package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.SeatSeatNo;

@Repository
public interface SeatSeatNoRepo extends JpaRepository<SeatSeatNo,Long> {
    
}
