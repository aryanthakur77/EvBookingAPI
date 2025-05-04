package org.ncu.evbookingapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.repository.StationRepository;
import org.ncu.evbookingapplication.service.StationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
@Tag(name = "Station Management", description = "APIs for managing stations")
public class StationController {

    private final StationRepository stationRepo;
    private final StationService stationService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Add a station")
    public Station addStation(@RequestBody Station station) {
        return stationService.addStation(station);
    }
    
    @GetMapping
    @Operation(summary = "Get all stations")
    public List<Station> getStations(@RequestParam(required = false) String city) {
        return city == null ? stationRepo.findAll() : stationRepo.findByCity(city);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get station by ID")
    public Station getStation(@PathVariable Long id) {
        return stationRepo.findById(id).orElseThrow(() -> new RuntimeException("Station not found"));
    }

}
