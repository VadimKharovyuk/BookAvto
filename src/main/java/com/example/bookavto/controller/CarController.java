package com.example.bookavto.controller;
import com.example.bookavto.model.Car;
import com.example.bookavto.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@AllArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "car_list";
    }

    @GetMapping("/cars/new")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "add_car";
    }

    @PostMapping("/cars")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }
    // Метод для удаления машины
    @PostMapping("/cars/delete")
    public String deleteCar(@RequestParam("carId") Long carId) {
        carService.deleteCar(carId);  // Удаление машины по ID
        return "redirect:/cars";  // Перенаправление после удаления
    }
}
