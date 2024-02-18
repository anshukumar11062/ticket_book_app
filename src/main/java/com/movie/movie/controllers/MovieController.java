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
    // Opening Index Page
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

    // Book Ticket Screen
    @GetMapping("booking")
    public String bookMovieView(@RequestParam("movieName") String movieName, Model m, HttpSession session) {
        List<String> seatNo1 = new ArrayList<String>();
        m.addAttribute("seats", seatNo1);
        LocalDate now=LocalDate.now();
        LocalDate max=now.plusDays(7);

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String maxDate=max.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<String> bookedSeats=seatRepo.getAllByDate(now,movieName);

        for(String item:bookedSeats){
                seatNo1.add(item);
        }

        session.setAttribute("movieName", movieName);
        m.addAttribute("date", date);
        m.addAttribute("min", date);
        m.addAttribute("max", maxDate);
        return "booking";
    }

    // Get the sheet bookings by date filter
    @GetMapping("filter-booking")
    public String bookShowByFilterDate(@RequestParam("movieName") String movieName,@RequestParam("filterDate") String filterDate, Model m, HttpSession session){
        List<String> seatNo1 = new ArrayList<String>();
        m.addAttribute("seats", seatNo1);
        LocalDate now=LocalDate.now();
        LocalDate max=now.plusDays(7);
        LocalDate localFilterDate=LocalDate.parse(filterDate);

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String maxDate=max.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String selectedDate=localFilterDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<String> bookedSeats=seatRepo.getAllByDate(localFilterDate,movieName);

        for(String item:bookedSeats){
                seatNo1.add(item);
        }

        session.setAttribute("movieName", movieName);
        m.addAttribute("date", selectedDate);
        m.addAttribute("min", date);
        m.addAttribute("max", maxDate);
        return "booking";
    }

    // Login Form
    @GetMapping("login")
    public String loginForm(HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }

        return "login";
    }

    // Register
    @GetMapping("register")
    public String register() {
        return "register";
    }

    // Get the List of all booked orders By users
    @GetMapping("my-orders")
    public String myOrders(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<OrderHistory> myOrderHistoryByUserId=orderHistoryRepo.findByUserId(user.getId());
        model.addAttribute("myOrders",myOrderHistoryByUserId);
        return "my-orders";
    }

    // Register User
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

    // User Login
    @PostMapping("login-fun")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
            HttpSession session, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
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

    // Sign Out
    @GetMapping("logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
        }

        return "redirect:/";
    }

    // Session Exception Handler Function
    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model m, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else
            return "redirect:/";
    }
}
