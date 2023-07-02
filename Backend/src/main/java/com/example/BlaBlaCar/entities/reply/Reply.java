package com.example.BlaBlaCar.entities.reply;

import com.example.BlaBlaCar.entities.group.Group;
import com.example.BlaBlaCar.entities.message.Message;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ownerUsername;

    private String text;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Message message;

    public Reply() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        return id != null && id.equals(((Reply) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
