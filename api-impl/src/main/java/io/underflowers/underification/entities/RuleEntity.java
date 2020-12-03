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
    @JoinColumns({
            @JoinColumn(name = "badge_name", referencedColumnName = "name", nullable=false),
            @JoinColumn(name = "application_id", referencedColumnName = "application", nullable=false)
    })
    private BadgeEntity badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointScales_id", nullable=true)
    private PointScaleEntity pointScale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applications_id", nullable=false)
    private ApplicationEntity application;
}
