package com.movie.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.movie.movie.DAO.UserType;
import com.movie.movie.DTO.BookTicketReqs;
import com.movie.movie.entities.DateOperation;
import com.movie.movie.entities.OrderHistory;
import com.movie.movie.entities.OrderHistoryPrice;
import com.movie.movie.entities.OrderHistorySeat;
import com.movie.movie.entities.Seat;
import com.movie.movie.entities.SeatSeatNo;
import com.movie.movie.entities.SeatSeatPrice;
import com.movie.movie.entities.User;
import com.movie.movie.repositories.DateOperationRepository;
import com.movie.movie.repositories.OrderHistoryPriceRepo;
import com.movie.movie.repositories.OrderHistoryRepository;
import com.movie.movie.repositories.OrderHistorySeatRepo;
import com.movie.movie.repositories.SeatRepository;
import com.movie.movie.repositories.SeatSeatNoRepo;
import com.movie.movie.repositories.SeatSeatPriceRepo;

import jakarta.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Description - Containing all the business logics for ticket bookings, List
 * all the tickets
 * | Status - Closed
 */

@Service
public class BookTicketService {

    @Autowired
    private DateOperationRepository dateOpsRepo;
    @Autowired
    private OrderHistoryRepository orderHistoryRepo;
    @Autowired
    private OrderHistorySeatRepo orderHistorySeatRepo;
    @Autowired
    private OrderHistoryPriceRepo orderHistoryPriceRepo;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatSeatNoRepo seatSeatNoRepo;
    @Autowired
    private SeatSeatPriceRepo seatSeatPriceRepo;

    /**
     * @param seat
     * @return boolean
     */
    public boolean bookTicket(BookTicketReqs seat, String movieName, User user) {
        // Variable Assignments
        double sumTotalPrice = 0.00;
        double seatPrice = 500;

        String movieTime = "09:00 pm";
        LocalDate currentDate = LocalDate.now();
        DateOperation dateOps = new DateOperation();
        OrderHistory eOrderHistory = new OrderHistory();
        String dateString = seat.getBookDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate;
        Seat eSeat = new Seat();

        // Derivative Assignments ======================================================

        // Convert LocalDate to Date
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // Create Date First
        dateOps.setShowDate(date);
        dateOps.setShowTime(movieTime);
        dateOpsRepo.save(dateOps);

        // Calculate Total Seat Price
        for (String item : seat.getSeatNo()) {
            sumTotalPrice += seatPrice;
            System.out.println(item);
        }
        System.out.println(sumTotalPrice);

        // Save Seat
        eSeat.setDateOperationId(dateOps.getId());
        eSeat.setTotal(sumTotalPrice);
        eSeat.setUserId(user.getId());
        eSeat.setMovieName(movieName);
        seatRepository.save(eSeat);

        // // Insert into order history
        eOrderHistory.setTotal(sumTotalPrice);
        eOrderHistory.setBookOnDate(date);
        // eOrderHistory.setBookOnTime(movieTime);
        eOrderHistory.setMovieName(movieName);
        eOrderHistory.setUserId(user.getId());
        eOrderHistory.setShowTime(movieTime);

        try {
            myDate = formatter.parse(dateString);
            eOrderHistory.setShowOnDate(myDate);
            orderHistoryRepo.save(eOrderHistory);
            // Update Order Seat id on Seat Table
            eSeat.setOrderHistoryId(eOrderHistory.getId());
            seatRepository.save(eSeat);

            // Insert Into Order History Seat and Seat Price
            for (String item : seat.getSeatNo()) {
                OrderHistorySeat orderHistorySeat = new OrderHistorySeat();
                orderHistorySeat.setOrderHistoryId(eOrderHistory.getId());
                orderHistorySeat.setSeat(item);
                orderHistorySeatRepo.save(orderHistorySeat);

                // Insert Seat Price
                OrderHistoryPrice orderHistoryPrice = new OrderHistoryPrice();
                orderHistoryPrice.setOrderHistoryId(eOrderHistory.getId());
                orderHistoryPrice.setPrice(seatPrice);
                orderHistoryPriceRepo.save(orderHistoryPrice);

                // Insert Seat No
                SeatSeatNo eSeatSeatNo = new SeatSeatNo();
                eSeatSeatNo.setSeatId(eSeat.getId());
                eSeatSeatNo.setSeatNo(item);
                seatSeatNoRepo.save(eSeatSeatNo);

                // Insert Seat Price
                SeatSeatPrice eSeatSeatPrice = new SeatSeatPrice();
                eSeatSeatPrice.setPrice(seatPrice);
                eSeatSeatPrice.setSeatId(eSeat.getId());
                seatSeatPriceRepo.save(eSeatSeatPrice);

            }

            return true;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

    /**
     * | Get All the Dashboard Reports and Customer
     */
    public boolean getAllTickets(Model model, HttpSession session, String date) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return false;
        }

        if (user.getUserType() == UserType.ADMIN) {
            List<Map<String, Object>> getAllOrderHistory = orderHistoryRepo.getAllOrders();
            model.addAttribute("totalOrders", getAllOrderHistory);

            // Dashboard Records
            List<Map<String, Object>> dashboardRecords = orderHistoryRepo.dashboardRecords(date);
            model.addAttribute("total_movies", dashboardRecords.get(0).get("total_movies"));
            model.addAttribute("total_seats", dashboardRecords.get(0).get("total_seats"));
            model.addAttribute("sold_tickets", dashboardRecords.get(0).get("sold_tickets"));
            model.addAttribute("tickets_available", dashboardRecords.get(0).get("tickets_available"));
            model.addAttribute("today_total_revenue", dashboardRecords.get(0).get("today_total_revenue"));

            model.addAttribute("date", date);
            return true;
        }

        return false;
    }

}
