package com.example.bookavto.controller;

import com.example.bookavto.model.Booking;
import com.example.bookavto.model.Car;
import com.example.bookavto.model.User;
import com.example.bookavto.service.BookingService;
import com.example.bookavto.service.CarService;
import com.example.bookavto.service.EmailSenderService;
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
    private final EmailSenderService emailSenderService;

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
            @RequestParam("clientEmail") String clientEmail,
            @RequestParam("clientId") Long clientId,
            @RequestParam("carId") Long carId,
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        // Получаем клиента из базы данных
        User client = userService.getUserById(clientId).orElseThrow(
                () -> new IllegalArgumentException("Client not found with ID: " + clientId)
        );

        // Получаем машину из базы данных
        Car car = carService.getCarById(carId).orElseThrow(
                () -> new IllegalArgumentException("Car not found with ID: " + carId)
        );

        // Создание объекта бронирования
        Booking booking = new Booking();
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setCar(car);  // Установка машины
        booking.setClient(client);  // Установка клиента

        bookingService.createBooking(booking);

        // Форматированный текст электронного письма
        String emailContent = String.format(
                "Спасибо, что выбрали наш сервис!\n\n" +
                        "Мы рады подтвердить ваше бронирование с следующими деталями:\n" +
                        "\nМарка машины: %s" +
                        "\nПериод бронирования: с %s по %s" +
                        "\nКлиент: %s" +
                        "\n\nВаше бронирование подтверждено, и мы с нетерпением ждем встречи с вами. Пожалуйста, убедитесь, что у вас есть действительные водительские права и другие необходимые документы в день начала бронирования." +
                        "\n\nЕсли у вас есть вопросы, вы можете связаться с нашей службой поддержки по телефону +123 456 789 или по электронной почте support@bookavto.com." +
                        "\n\nМы ценим ваш выбор и желаем вам приятного опыта. Не стесняйтесь обращаться к нам, если вам потребуется дополнительная помощь." +
                        "\n\nС уважением,\nКоманда LuckyBookAvto",
                car.getBrand(),  // Бренд машины
                startDate.toString(),  // Дата начала
                endDate.toString(),  // Дата окончания
                client.getName()  // Имя клиента
        );


        // Отправка электронного письма
        emailSenderService.sendEmail(
                clientEmail,
                "Booking Confirmation",
                emailContent
        );

        return "redirect:/bookings";  // Перенаправление после создания бронирования
    }
    @GetMapping("/bookings/calculate") // Маршрут, который обрабатывает запросы на расчет стоимости
    public String calculateBookingCost(
            @RequestParam("carId") Long carId,  // ID машины
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);  // Вычисление дней

        // Рассчитываем стоимость на основе количества дней
        double cost = bookingService.calculateBookingCost(carId, (int) numberOfDays);

        // Передаем данные в представление
        model.addAttribute("cost", cost);
        model.addAttribute("bookingId", carId);  // ID бронирования
        model.addAttribute("startDate", startDate);  // Дата начала
        model.addAttribute("endDate", endDate);  // Дата окончания

        return "booking_cost";  // Возвращаем страницу с рассчитанной стоимостью
    }

    @PostMapping("/bookings/delete")
    public String deleteBooking(@RequestParam("bookingId") Long bookingId) {
        bookingService.deleteBooking(bookingId);  // Удаление бронирования
        return "redirect:/bookings";  // Перенаправление после удаления
    }
}
