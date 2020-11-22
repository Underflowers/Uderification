package io.underflowers.underification.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class PointScaleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable=false)
    private ApplicationEntity application;
}
