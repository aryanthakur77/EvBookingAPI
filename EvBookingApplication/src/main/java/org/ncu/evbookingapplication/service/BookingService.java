package org.ncu.evbookingapplication.service;

import lombok.RequiredArgsConstructor;
import org.ncu.evbookingapplication.entity.Booking;
import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.repository.BookingRepository;
import org.ncu.evbookingapplication.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;
    private final StationRepository stationRepo;

    public Booking createBooking(Long stationId, Booking booking) {
        Station station = stationRepo.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        boolean exists = bookingRepo.existsByStationAndBookingTime(station, booking.getBookingTime());
        if (exists) throw new RuntimeException("Time slot already booked");

        booking.setStation(station);
        return bookingRepo.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        bookingRepo.deleteById(bookingId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}

