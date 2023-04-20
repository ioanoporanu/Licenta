package com.example.BlaBlaCar.entities.location;

import com.example.BlaBlaCar.entities.group.Group;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float longitude;

    private float latitude;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        return id != null && id.equals(((Group) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
