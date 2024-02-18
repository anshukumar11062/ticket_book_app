package com.movie.movie.DTO;


public class OrderHistoryRes {
	private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    } 

    public OrderHistoryRes(String id){
        this.id=id;
    }
}
