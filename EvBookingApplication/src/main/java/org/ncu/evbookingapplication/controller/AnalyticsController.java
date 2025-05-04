package org.ncu.evbookingapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ncu.evbookingapplication.dto.StationUsageDTO;
import org.ncu.evbookingapplication.service.AnalyticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Analytics", description = "APIs for analyzing station usage data")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/station-usage")
    @Operation(summary = "Get booking count for all stations")
    public List<StationUsageDTO> getStationUsageStats() {
        return analyticsService.getStationUsageStats();
    }

    @GetMapping("/top-stations")
    @Operation(summary = "Get top busiest stations based on bookings")
    public List<StationUsageDTO> getTop3BusiestStations(@RequestParam(defaultValue = "3") int limit) {
        return analyticsService.getTopBusiestStations(limit);
    }
}
