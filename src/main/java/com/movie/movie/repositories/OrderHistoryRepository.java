package com.movie.movie.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.movie.entities.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    public List<OrderHistory> findByUserId(Long userId);

    @Query(value="SELECT o.*,u.name AS username FROM order_history o JOIN user u ON u.id=o.user_id order by o.id desc",nativeQuery = true)
    public List<Map<String,Object>> getAllOrders();

    @Query(value = "SELECT *, (total_seats - sold_tickets) AS tickets_available " +
               "FROM " +
               "( " +
               "    SELECT * " +
               "    FROM " +
               "    ( " +
               "        SELECT COUNT(id) AS total_movies FROM movie_masters " +
               "    ) AS movie_mstrs, " +
               "    ( " +
               "        SELECT 40 * (SELECT COUNT(id) FROM movie_masters) AS total_seats " +
               "    ) AS total_seats, " +
               "    ( " +
               "        SELECT COUNT(id) AS sold_tickets, IFNULL(SUM(total),0) AS today_total_revenue " +
               "        FROM order_history " +
               "        WHERE show_on_date = ? AND is_cancelled = 0 " +          // Show ON Date Should be Parameterised as per the date
               "    ) AS sold_tickets " +
               ") AS tickets", nativeQuery = true)
    public List<Map<String,Object>> dashboardRecords(String date);
}
