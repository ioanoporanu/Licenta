package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.entities.message.Message;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Message> messages;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "users_owned_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<User> owners = new HashSet<>();

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group )) return false;
        return id != null && id.equals(((Group) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
