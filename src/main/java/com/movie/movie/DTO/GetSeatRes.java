package com.movie.movie.DTO;


public class GetSeatRes {
    
    private Integer id;
    private String seatNo;

    public GetSeatRes(Integer id, String seatNo) {
        this.id = id;
        this.seatNo = seatNo;
    }


    public GetSeatRes(String seatNo){
        this.seatNo=seatNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
}
