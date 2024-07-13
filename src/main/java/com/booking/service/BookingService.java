package com.booking.service;

import com.booking.model.Booking;
import com.booking.model.Car;
import com.booking.model.User;
import com.booking.repository.BookingRepository;
import com.booking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    public Booking createBooking(UUID carId, Booking booking, String username) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isPresent()) {
            booking.setId(UUID.randomUUID());
            booking.setCode(UUID.randomUUID().toString().replace("-", ""));

            Car car = carOptional.get();
            booking.setCar(car);

            User user = userService.findByUsername(username);
            booking.setUser(user);

            long daysDiff = ChronoUnit.DAYS.between(booking.getEndDate(), booking.getStartDate());
            booking.setTotalPrice(daysDiff * car.getDailyPrice());
            return bookingRepository.save(booking);
        }
        else {
            return new Booking();
        }
    }

    public Optional<Booking> getBookingByCode(String code) {
        return bookingRepository.findBookingByCode(code);
    }
}
