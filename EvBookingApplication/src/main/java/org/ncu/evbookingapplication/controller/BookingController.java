package org.ncu.evbookingapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ncu.evbookingapplication.entity.Booking;
import org.ncu.evbookingapplication.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('EV_OWNER')")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{stationId}")
    @Operation(summary = "Add a new booking")
    public Booking bookSlot(@PathVariable Long stationId, @RequestBody Booking booking) {
        return bookingService.createBooking(stationId, booking);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel a booking")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

    @GetMapping
    @Operation(summary = "Get all bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }
}

