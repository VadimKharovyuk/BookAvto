package com.example.bookavto.service;

import com.example.bookavto.model.Booking;
import com.example.bookavto.model.Car;
import com.example.bookavto.repository.BookingRepository;
import com.example.bookavto.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }



    public double calculateBookingCost(Long carId, int numberOfDays) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found"));
        double basePrice = car.getPricePerDay();

        // Множители для разных периодов бронирования
        double multiplier = 1.0;

        if (numberOfDays == 3) {
            multiplier = 1.05;  // На 5% больше
        } else if (numberOfDays == 5) {
            multiplier = 1.10;  // На 10% больше
        } else if (numberOfDays == 6) {
            multiplier = 1.15;  // На 15% больше
        }

        return basePrice * numberOfDays * multiplier;  // Рассчитываем итоговую стоимость
    }
}
