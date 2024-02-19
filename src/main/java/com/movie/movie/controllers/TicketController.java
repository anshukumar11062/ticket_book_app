package com.movie.movie.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.movie.DTO.BookTicketReqs;
import com.movie.movie.entities.OrderHistory;
import com.movie.movie.entities.User;
import com.movie.movie.repositories.OrderHistoryRepository;
import com.movie.movie.services.BookTicketService;

import jakarta.servlet.http.HttpSession;

/**
 * | Author - Anshu Kumar
 * | Created On - 17/02/2024
 * | Seat Booking Logics and Web routes
 * | Status - Closed
 */
@Controller
public class TicketController {

    @Autowired
    private BookTicketService bookTicketService;

    @Autowired
    OrderHistoryRepository orderHistoryRepo;

    /**
     * @desc Seat Booking Process
     * @param seat
     * @param movieName
     * @param session
     * @param m
     * @return redirection to login page
     */
    @PostMapping("/book-seat")
    public String bookSeat(BookTicketReqs seat, @RequestParam("movieName") String movieName,
            HttpSession session, Model m) {
        // Variable Assignments
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "login";
        }

        boolean status = bookTicketService.bookTicket(seat, movieName, user);

        if (status) {
            return "redirect:/my-orders";
        }
        return "login";
    }

    /**
     * @desc ticket cancellation process
     * @param orderId
     * @param model
     * @param httpSession
     * @return
     */
    @PostMapping("/cancel-ticket")
    public String cancelTicket(@RequestParam("orderId") Integer orderId, Model model, HttpSession httpSession) {
        System.out.println(orderId);
        if (orderId == null) {
            return "redirect:/";
        }
        try {
            OrderHistory mOrderHistory = orderHistoryRepo.findById(Long.valueOf(orderId)).get();
            mOrderHistory.setIs_cancelled(true);
            orderHistoryRepo.save(mOrderHistory);

            return "redirect:/my-orders";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * @desc Get All the tickets for all users/customers (Accessable for Admin only)
     * @param model
     * @param session
     * @return
     */
    @GetMapping("get-all-tickets")
    public String getAllTickets(Model model, HttpSession session) {
        LocalDate currentDate = LocalDate.now();
        // Format the date as YYYY-MM-DD
        String date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        boolean status = bookTicketService.getAllTickets(model, session, date);
        if (status) {
            return "total-orders";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * @desc filter the booked tickets by date
     * @param model
     * @return
     */
    @GetMapping("get-tickets-by-date")
    public String getAllTicketsByDate(Model model, HttpSession session, @RequestParam("date") String date) {
        boolean status = bookTicketService.getAllTickets(model, session, date);
        if (status) {
            return "total-orders";
        } else {
            return "redirect:/login";
        }
    }
}
