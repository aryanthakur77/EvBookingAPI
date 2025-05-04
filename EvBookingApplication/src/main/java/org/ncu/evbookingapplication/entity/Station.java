package org.ncu.evbookingapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private int totalPorts;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Booking> bookings;

    public Station(String name, String city, int totalPorts) {
        this.name = name;
        this.city = city;
        this.totalPorts = totalPorts;
    }

}
