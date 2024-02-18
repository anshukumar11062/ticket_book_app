package com.movie.movie.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    
    @Query(value="select seat_seat_no.seat_no from seat inner join seat_seat_no" 
                +" on seat.id = seat_seat_no.seat_id"
                +" inner join date_operation" 
                +" on seat.date_operation_id = date_operation.id" 
                +" JOIN order_history oh ON oh.id=seat.order_history_id"
                +" where show_on_date = ? and seat.movie_name = ? AND oh.is_cancelled=false",nativeQuery = true)
    public List<String> getAllByDate(LocalDate date,String movieName);
}
