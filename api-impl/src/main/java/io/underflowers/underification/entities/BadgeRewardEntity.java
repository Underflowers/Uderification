package io.underflowers.underification.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BadgeRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "badge_name", referencedColumnName = "name", nullable=false),
            @JoinColumn(name = "application_id", referencedColumnName = "application", nullable=false)
    })
    private BadgeEntity badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable=false)
    private UserEntity user;
}
