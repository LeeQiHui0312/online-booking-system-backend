package com.booking.controller;

import com.booking.model.Booking;
import com.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/{carId}/{username}")
    public ResponseEntity<Booking> createBooking(
            @PathVariable UUID carId,
            @PathVariable String username,
            @RequestBody Booking booking
    ) {
        Booking createdBooking = bookingService.createBooking(carId, booking, username);
        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping("/{bookingCode}")
    public ResponseEntity<Booking> getBookingByCode(@PathVariable String bookingCode) {
        Optional<Booking> booking = bookingService.getBookingByCode(bookingCode);
        return booking.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

