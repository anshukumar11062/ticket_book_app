package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.OrderHistoryPrice;

@Repository
public interface OrderHistoryPriceRepo extends JpaRepository<OrderHistoryPrice,Long> {
    
}
