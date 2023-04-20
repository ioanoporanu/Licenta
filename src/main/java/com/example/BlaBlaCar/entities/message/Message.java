package com.example.BlaBlaCar.entities.message;

import com.example.BlaBlaCar.entities.group.Group;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    private String text;

    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message )) return false;
        return id != null && id.equals(((Message) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
