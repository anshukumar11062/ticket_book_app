package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.SeatSeatPrice;

@Repository
public interface SeatSeatPriceRepo extends JpaRepository<SeatSeatPrice,Long> {
    
}
