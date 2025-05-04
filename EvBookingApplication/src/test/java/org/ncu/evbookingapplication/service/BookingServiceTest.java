package org.ncu.evbookingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ncu.evbookingapplication.entity.Booking;
import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.repository.BookingRepository;
import org.ncu.evbookingapplication.repository.StationRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingRepository bookingRepo;
    private StationRepository stationRepo;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepo = mock(BookingRepository.class);
        stationRepo = mock(StationRepository.class);
        bookingService = new BookingService(bookingRepo, stationRepo);
    }

    @Test
    void testCreateBooking_Successful() {
        Long stationId = 1L;
        Station station = new Station();

        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        Date bookingTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Booking booking = new Booking();
        booking.setBookingTime(bookingTime);

        when(stationRepo.findById(stationId)).thenReturn(Optional.of(station));
        when(bookingRepo.existsByStationAndBookingTime(station, bookingTime)).thenReturn(false);
        when(bookingRepo.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Booking created = bookingService.createBooking(stationId, booking);

        assertEquals(station, created.getStation());
        verify(bookingRepo, times(1)).save(booking);
    }

    @Test
    void testCreateBooking_StationNotFound() {
        Long stationId = 100L;
        Booking booking = new Booking();
        when(stationRepo.findById(stationId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(stationId, booking);
        });

        assertEquals("Station not found", exception.getMessage());
    }

    @Test
    void testCreateBooking_DuplicateTimeSlot() {
        Long stationId = 1L;
        Station station = new Station();

        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        Date bookingTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Booking booking = new Booking();
        booking.setBookingTime(bookingTime);

        when(stationRepo.findById(stationId)).thenReturn(Optional.of(station));
        when(bookingRepo.existsByStationAndBookingTime(station, bookingTime)).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(stationId, booking);
        });

        assertEquals("Time slot already booked", exception.getMessage());
    }

    @Test
    void testCancelBooking() {
        Long bookingId = 5L;
        bookingService.cancelBooking(bookingId);
        verify(bookingRepo, times(1)).deleteById(bookingId);
    }

    @Test
    void testGetAllBookings() {
        List<Booking> mockBookings = Arrays.asList(new Booking(), new Booking());
        when(bookingRepo.findAll()).thenReturn(mockBookings);

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(2, result.size());
        verify(bookingRepo, times(1)).findAll();
    }

    @Test
    void testGetBookingById_Found() {
        Long bookingId = 3L;
        Booking booking = new Booking(); // No need to set ID manually
        when(bookingRepo.findById(bookingId)).thenReturn(Optional.of(booking));

        Booking result = bookingService.getBookingById(bookingId);

        assertEquals(booking, result);
    }

    @Test
    void testGetBookingById_NotFound() {
        Long bookingId = 404L;
        when(bookingRepo.findById(bookingId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.getBookingById(bookingId);
        });

        assertEquals("Booking not found", exception.getMessage());
    }
}
