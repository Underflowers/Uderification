package io.underflowers.underification.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applications_id", nullable=false)
    private ApplicationEntity application;
}
