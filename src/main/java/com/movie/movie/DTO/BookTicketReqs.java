package com.movie.movie.DTO;

import java.util.List;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Request paramets to book the tickets
 * | Status - Closed
 */

public class BookTicketReqs {

    private List<String> seatNo;
    private String bookDate;

    public List<String> getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(List<String> seatNo) {
        this.seatNo = seatNo;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

}
