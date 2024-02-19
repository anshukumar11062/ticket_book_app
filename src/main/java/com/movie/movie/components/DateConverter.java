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

    /**
     * @desc Get current date in format string
     * @return
     */
    public String getCurrentDate() {
        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format the date to "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        // Return the formatted date
        return formattedDate;
    }

}
