package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.location.Location;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int availableSeats;

    @ManyToMany(mappedBy = "rides")
    private List<User> customers;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToOne
    private Location destination;

    @OneToOne
    private Location source;

    private LocalDateTime rideDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ride )) return false;
        return id != null && id.equals(((Ride) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
