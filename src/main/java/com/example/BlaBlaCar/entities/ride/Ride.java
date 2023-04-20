package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.location.Location;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "rides")
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToOne
    private Location destination;

    @OneToOne
    private Location source;

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
