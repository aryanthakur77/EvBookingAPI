package org.ncu.evbookingapplication.repository;

import org.ncu.evbookingapplication.dto.StationUsageDTO;
import org.ncu.evbookingapplication.entity.Station;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByCity(String city);

    boolean existsByNameAndCity(String name, String city);

    @Query("SELECT new org.ncu.evbookingapplication.dto.StationUsageDTO(s.name, COUNT(b)) " +
            "FROM Station s LEFT JOIN Booking b ON s.id = b.station.id " +
            "GROUP BY s.id")
    List<StationUsageDTO> getStationBookingCounts();

    @Query("SELECT new org.ncu.evbookingapplication.dto.StationUsageDTO(s.name, COUNT(b)) " +
            "FROM Station s LEFT JOIN Booking b ON s.id = b.station.id " +
            "GROUP BY s.id ORDER BY COUNT(b) DESC")
    List<StationUsageDTO> getTopBusiestStations(Pageable pageable);

}

