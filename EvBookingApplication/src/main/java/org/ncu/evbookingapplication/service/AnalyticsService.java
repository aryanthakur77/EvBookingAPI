package org.ncu.evbookingapplication.service;

import lombok.RequiredArgsConstructor;
import org.ncu.evbookingapplication.dto.StationUsageDTO;
import org.ncu.evbookingapplication.repository.StationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final StationRepository stationRepository;

    public List<StationUsageDTO> getStationUsageStats() {
        return stationRepository.getStationBookingCounts();
    }

    public List<StationUsageDTO> getTopBusiestStations(int limit) {
        return stationRepository.getTopBusiestStations(PageRequest.of(0, limit));
    }
}
