package com.booking.controller;

import com.booking.model.Car;
import com.booking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.saveCar(car);
        return ResponseEntity.ok(savedCar);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable UUID carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok("Car deleted successfully");
    }
}
