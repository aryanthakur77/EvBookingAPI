package org.ncu.evbookingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StationUsageDTO {
    private String stationName;
    private long bookingCount;

    public StationUsageDTO(){}
    public StationUsageDTO(long bookingCount, String stationName) {
        this.bookingCount = bookingCount;
        this.stationName = stationName;
    }


    public long getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(long bookingCount) {
        this.bookingCount = bookingCount;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}

