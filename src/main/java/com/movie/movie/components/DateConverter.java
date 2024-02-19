package com.movie.movie.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * | Author - Anshu Kumar
 * | Created On - 18 Feb 2024
 * | Created For the helper function to convert string date to LocalDate
 * | Status - Closed
 */

@Component
public class DateConverter {

    /**
     * @param dateString
     * @return LocalDate
     */
    public LocalDate stringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

}
