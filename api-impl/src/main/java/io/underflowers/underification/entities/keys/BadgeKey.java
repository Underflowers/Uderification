package io.underflowers.underification.entities.keys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class BadgeKey implements Serializable {

    private String name;

    //@ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "application_id", nullable=false)
    private Long application;

    public BadgeKey(String name, Long application) {
        this.name = name;
        this.application = application;
    }

    public BadgeKey() {}
}
