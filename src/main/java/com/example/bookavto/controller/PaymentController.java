package com.example.bookavto.controller;

import com.example.bookavto.model.Booking;
import com.example.bookavto.model.Payment;
import com.example.bookavto.service.BookingService;
import com.example.bookavto.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingService bookingService;

    @GetMapping("/payments")
    public String getAllPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();  // Убедитесь, что данные присутствуют
        model.addAttribute("payments", payments);
        return "payment_list";
    }


    @GetMapping("/payments/new")
    public String showCreatePaymentForm(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("payment", new Payment());
        model.addAttribute("bookings", bookings);
        return "create_payment";
    }

    @PostMapping("/payments")
    public String createPayment(
            @RequestParam("bookingId") Long bookingId,
            @RequestParam("paymentDate") LocalDate paymentDate,
            @RequestParam("amount") double amount
    ) {
        Payment payment = new Payment();
        Booking booking = bookingService.getBookingById(bookingId);  // Проверка на null

        if (booking == null) {
            throw new IllegalArgumentException("Booking not found for ID: " + bookingId);  // Обработка ошибки
        }

        payment.setBooking(booking);
        payment.setPaymentDate(paymentDate);
        payment.setAmount(amount);

        paymentService.createPayment(payment);

        return "redirect:/payments";
    }



}
