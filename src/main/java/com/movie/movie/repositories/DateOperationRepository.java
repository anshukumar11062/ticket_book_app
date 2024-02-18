package com.movie.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.movie.entities.DateOperation;

public interface DateOperationRepository extends JpaRepository<DateOperation, Long> {

}
