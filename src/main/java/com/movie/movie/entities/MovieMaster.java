package com.movie.movie.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_masters")
public class MovieMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private String image;

    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String movieDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(String movieDetails) {
        this.movieDetails = movieDetails;
    }

}
