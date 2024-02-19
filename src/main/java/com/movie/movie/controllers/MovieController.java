package com.movie.movie.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.movie.DAO.UserType;
import com.movie.movie.entities.MovieMaster;
import com.movie.movie.entities.OrderHistory;
import com.movie.movie.entities.User;
import com.movie.movie.repositories.MovieMasterRepository;
import com.movie.movie.repositories.OrderHistoryRepository;
import com.movie.movie.repositories.SeatRepository;
import com.movie.movie.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * | Author - Anshu Kumar
 * | Created On - 18 Feb 2024
 * | Created For the controller for Movie resources and web
 * | Status - Closed
 */

@Controller
public class MovieController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieMasterRepository movieRepo;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private OrderHistoryRepository orderHistoryRepo;

    /**
     * 
     * @param session
     * @param m
     * @return Index Page
     */
    @GetMapping("/")
    public String home(HttpSession session, Model m) {
        m.addAttribute("isAuthenticated", false); // Initial State for Authenticated User
        m.addAttribute("isAdmin", false); // Initial State
        User user = (User) session.getAttribute("user");
        if (user != null) {
            m.addAttribute("isAuthenticated", true);
            m.addAttribute("username", user.getName());
            if (user.getUserType() == UserType.ADMIN) {
                m.addAttribute("isAdmin", true); // Initial State

            }
        }

        // Get Movie Records
        List<MovieMaster> movies = movieRepo.findAll();
        m.addAttribute("movieList", movies);
        return "index";
    }

    /**
     * @desc Ticket Booking for multiple rows
     * @param movieName
     * @param m
     * @param session
     * @return
     */
    @GetMapping("booking")
    public String bookMovieView(@RequestParam("movieName") String movieName, Model m, HttpSession session) {
        List<String> seatNo1 = new ArrayList<String>();
        m.addAttribute("seats", seatNo1);
        LocalDate now = LocalDate.now();
        LocalDate max = now.plusDays(7);

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String maxDate = max.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<String> bookedSeats = seatRepo.getAllByDate(now, movieName);

        for (String item : bookedSeats) {
            seatNo1.add(item);
        }

        session.setAttribute("movieName", movieName);
        m.addAttribute("date", date);
        m.addAttribute("min", date);
        m.addAttribute("max", maxDate);
        return "booking";
    }

    /**
     * @desc Get the sheet bookings by date filter
     * @param movieName
     * @param filterDate
     * @param m
     * @param session
     * @return
     */
    @GetMapping("filter-booking")
    public String bookShowByFilterDate(@RequestParam("movieName") String movieName,
            @RequestParam("filterDate") String filterDate, Model m, HttpSession session) {
        List<String> seatNo1 = new ArrayList<String>();
        m.addAttribute("seats", seatNo1);
        LocalDate now = LocalDate.now();
        LocalDate max = now.plusDays(7);
        LocalDate localFilterDate = LocalDate.parse(filterDate);

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String maxDate = max.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String selectedDate = localFilterDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<String> bookedSeats = seatRepo.getAllByDate(localFilterDate, movieName);

        for (String item : bookedSeats) {
            seatNo1.add(item);
        }

        session.setAttribute("movieName", movieName);
        m.addAttribute("date", selectedDate);
        m.addAttribute("min", date);
        m.addAttribute("max", maxDate);
        return "booking";
    }

    /**
     * @desc Login Page
     * @param session
     * @return
     */
    @GetMapping("login")
    public String loginForm(HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }

        return "login";
    }

    /**
     * @desc opening registration page
     * @return register page
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * @desc Get the List of all booked orders By users
     * @param model
     * @param session
     * @return my-orders page
     */
    @GetMapping("my-orders")
    public String myOrders(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        LocalDate now = LocalDate.now();
        if (user == null) {
            return "redirect:/login";
        }

        List<OrderHistory> myOrderHistoryByUserId = orderHistoryRepo.findByUserId(user.getId());
        model.addAttribute("myOrders", myOrderHistoryByUserId);
        model.addAttribute("now", now);
        return "my-orders";
    }

    /**
     * @desc register for general users
     * @note (Admins are to be added by backend)
     * @param user
     * @param redirectAttributes
     * @return
     */
    @PostMapping("register-user")
    public String registerUser(@ModelAttribute("users") User user, RedirectAttributes redirectAttributes) {
        // Check if the User already Existing
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            redirectAttributes.addFlashAttribute("error", "Registration Failed! Email already Existing");
            return "redirect:/register";
        }

        user.setUserType(UserType.USER);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration Successfull! Please Continue to login");
        return "redirect:/login";
    }

    /**
     * @desc User authentication logic flow
     * @param email
     * @param password
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("login-fun")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
            HttpSession session, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user != null) { // If the user is already logged in
            return "redirect:/";
        }

        if (user == null) {
            User existingUser = userRepository.findByEmailAndPassword(email, password);
            if (existingUser == null) {
                redirectAttributes.addFlashAttribute("error", "Invalid Credentials");
                return "redirect:/login";
            }

            if (existingUser != null) {
                session.setAttribute("user", existingUser);
            }

        }
        return "redirect:/";
    }

    /**
     * @desc logout for authenticated users
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
        }

        return "redirect:/";
    }

    /**
     * @desc Session Exception Handler Function
     * @param ex
     * @param m
     * @param session
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model m, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else
            return "redirect:/";
    }
}
