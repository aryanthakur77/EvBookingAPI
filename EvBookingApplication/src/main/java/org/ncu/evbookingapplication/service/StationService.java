package org.ncu.evbookingapplication.service;

import lombok.RequiredArgsConstructor;
import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public Station addStation(Station station) {
        boolean exists = stationRepository.existsByNameAndCity(station.getName(), station.getCity());
        if (exists) {
            throw new RuntimeException("Station with the same name and city already exists.");
        }
        return stationRepository.save(station);
    }

    public List<Station> getStations(String city) {
        if (city != null && !city.isEmpty()) {
            return stationRepository.findByCity(city);
        }
        return stationRepository.findAll();
    }

    public Station getStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with ID: " + id));
    }


    public void deleteStation(Long id) {
        if (!stationRepository.existsById(id)) {
            throw new RuntimeException("Station not found with ID: " + id);
        }
        stationRepository.deleteById(id);
    }
}

