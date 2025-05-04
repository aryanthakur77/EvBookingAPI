package org.ncu.evbookingapplication.repository;

import org.ncu.evbookingapplication.entity.Booking;
import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.dto.StationUsageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.Date;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByStationAndBookingTime(Station station, Date bookingTime);

    @Query("SELECT new org.ncu.evbookingapplication.dto.StationUsageDTO(s.name, COUNT(b)) " +
            "FROM Booking b JOIN b.station s GROUP BY s.id ORDER BY COUNT(b) DESC")
    List<StationUsageDTO> getStationUsageStats();
}

