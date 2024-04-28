package com.example.bookavto.controller;

import com.example.bookavto.model.Booking;
import com.example.bookavto.model.Car;
import com.example.bookavto.model.User;
import com.example.bookavto.service.BookingService;
import com.example.bookavto.service.CarService;
import com.example.bookavto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final CarService carService;
    private final UserService userService;

    @GetMapping("/bookings")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking_list";
    }

    @GetMapping("/bookings/new")
    public String showCreateBookingForm(Model model) {
        List<Car> cars = carService.getAllCars();
        List<User> clients = userService.getAllUsers();
        model.addAttribute("booking", new Booking());
        model.addAttribute("cars", cars);
        model.addAttribute("clients", clients);
        return "create_booking";
    }

    @PostMapping("/bookings")
    public String createBooking(
            @RequestParam("clientId") Long clientId,
            @RequestParam("carId") Long carId,
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        Booking booking = new Booking();
        booking.setClient(new User(clientId));
        booking.setCar(new Car(carId));
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        bookingService.createBooking(booking);

        return "redirect:/bookings";
    }


}
