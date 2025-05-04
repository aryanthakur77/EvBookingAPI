package org.ncu.evbookingapplication;

import org.ncu.evbookingapplication.entity.Station;
import org.ncu.evbookingapplication.repository.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class EvBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvBookingApplication.class, args);
		System.out.println("EV Booking Application Initialized Successfully");
	}

	@Bean
	CommandLineRunner loadData(StationRepository repo) {
		return args -> {
			if (!repo.existsByNameAndCity("NorthCap EV", "Gurgaon")) {
				repo.save(new Station(null, "NorthCap EV", "Gurgaon", 4, new ArrayList<>()));
			}
			if (!repo.existsByNameAndCity("CyberHub Charging", "Gurgaon")) {
				repo.save(new Station(null, "CyberHub Charging", "Gurgaon", 3, new ArrayList<>()));
			}
		};
	}
}
