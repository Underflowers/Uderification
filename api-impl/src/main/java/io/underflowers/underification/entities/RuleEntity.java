package io.underflowers.underification.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class RuleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable=true)
    private BadgeEntity badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointScale_id", nullable=true)
    private PointScaleEntity pointScale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable=false)
    private ApplicationEntity application;
}
