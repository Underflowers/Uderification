package io.underflowers.underification.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BadgeAwardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // TODO private UserEntity user;
    // TODO private BadgeEntity badge;

    private LocalDateTime dateTime;
}
