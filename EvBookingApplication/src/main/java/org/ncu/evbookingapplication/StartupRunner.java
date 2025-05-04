package org.ncu.evbookingapplication;

import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private StationRepository stationRepo;

    @Override
    public void run(String... args) {
        addStationIfNotExists("NCU North", "Gurgaon", 4);
        addStationIfNotExists("Cyber Hub EV", "Gurgaon", 3);

        System.out.println("EV Booking Application Initialized Successfully");
    }

    private void addStationIfNotExists(String name, String city, int totalPorts) {
        if (!stationRepo.existsByNameAndCity(name, city)) {
            Station station = new Station(name, city, totalPorts);
            stationRepo.save(station);
            System.out.println("Station added: " + name);
        } else {
            System.out.println("Station already exists: " + name);
        }
    }
}
