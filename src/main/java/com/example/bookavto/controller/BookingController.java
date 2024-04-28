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
import java.time.temporal.ChronoUnit;
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

@GetMapping("/bookings/calculate")
public String calculateBookingCost(
        @RequestParam("carId") Long carId,  // Получение параметра carId
        @RequestParam("startDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("endDate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
        Model model
) {
    // Вычисление количества дней
    long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

    // Расчет стоимости
    double cost = bookingService.calculateBookingCost(carId, (int) numberOfDays);

    // Передача данных в модель
    model.addAttribute("cost", cost);
    model.addAttribute("bookingId", carId);
    model.addAttribute("startDate", startDate);
    model.addAttribute("endDate", endDate);

    // Возвращаем страницу с рассчитанной стоимостью
    return "booking_cost";


}
    @PostMapping("/bookings/delete")
    public String deleteBooking(@RequestParam("bookingId") Long bookingId) {
        bookingService.deleteBooking(bookingId);  // Удаление бронирования по ID
        return "redirect:/bookings";  // Перенаправление после удаления
    }


}
