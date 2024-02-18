package com.movie.movie.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private double total;

    private Long userId;
    private Long dateOperationId;
    private String movieName;
    private Long orderHistoryId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getDateOperationId() {
        return dateOperationId;
    }
    public void setDateOperationId(Long dateOperationId) {
        this.dateOperationId = dateOperationId;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public Long getOrderHistoryId() {
        return orderHistoryId;
    }
    public void setOrderHistoryId(Long orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }
    
}
