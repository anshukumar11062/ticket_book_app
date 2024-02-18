package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.OrderHistorySeat;


@Repository
public interface OrderHistorySeatRepo extends JpaRepository<OrderHistorySeat,Long> {
    
}
