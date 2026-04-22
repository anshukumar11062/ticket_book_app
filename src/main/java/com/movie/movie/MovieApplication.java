package com.movie.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024 
 * | description - Main file of the Project
 * | Status - Closed
 */

@SpringBootApplication
public class MovieApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MovieApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}