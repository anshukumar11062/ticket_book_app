package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.OrderHistoryPrice;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Status - Closed
 */

@Repository
public interface OrderHistoryPriceRepo extends JpaRepository<OrderHistoryPrice, Long> {

}
