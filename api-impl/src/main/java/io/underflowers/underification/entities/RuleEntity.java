package io.underflowers.underification.entities;

import io.underflowers.underification.api.model.PointScale;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class RuleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String eventType;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badges_id", nullable=true)
    private BadgeEntity badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointScales_id", nullable=true)
    private PointScaleEntity pointScale;
}
