package com.example.bookavto.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;  // Убедитесь, что `client` не является `null`

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Booking(Long bookingId) {
        this.id=bookingId;
    }

    public void setCarId(Long carId) {
        this.id=carId;
    }

    // Геттеры и сеттеры
}




