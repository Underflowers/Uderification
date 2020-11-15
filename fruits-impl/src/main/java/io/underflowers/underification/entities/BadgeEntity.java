package io.underflowers.underification.entities;

import io.underflowers.underification.api.model.Application;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class BadgeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String image;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable=false)
    private ApplicationEntity application;
}
