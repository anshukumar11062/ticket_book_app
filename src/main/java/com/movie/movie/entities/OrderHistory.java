package com.movie.movie.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Order History table schema definition
 * | Status - Closed
 */

@Entity
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "book_on_date")
    private Date bookOnDate;

    @Column(name = "movie_name")
    private String movieName;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "show_on_date")
    private Date showOnDate;

    @Column(name = "show_time")
    private String showTime;

    private double total;

    private Long userId;

    @Column(nullable = false)
    private Boolean is_cancelled = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBookOnDate() {
        return bookOnDate;
    }

    public void setBookOnDate(Date bookOnDate) {
        this.bookOnDate = bookOnDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getShowOnDate() {
        return showOnDate;
    }

    public void setShowOnDate(Date showOnDate) {
        this.showOnDate = showOnDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
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

    public Boolean getIs_cancelled() {
        return is_cancelled;
    }

    public void setIs_cancelled(Boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }

}
