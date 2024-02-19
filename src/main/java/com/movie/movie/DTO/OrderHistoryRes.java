package com.movie.movie.DTO;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Response paramets to get the order history
 * | Status - Closed
 */
public class OrderHistoryRes {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderHistoryRes(String id) {
        this.id = id;
    }
}
