package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.group.Group;
import com.example.BlaBlaCar.entities.location.Location;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> customers;

    @ManyToMany(mappedBy = "sharedRides")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Group> sharedGroups;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    @OneToOne(cascade=CascadeType.ALL)
    private Location destination;

    @OneToOne(cascade=CascadeType.ALL)
    private Location source;

    private Date rideDate;

    private float rideLength;

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
