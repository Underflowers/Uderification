package io.underflowers.underification.entities;

import io.underflowers.underification.entities.keys.BadgeKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class BadgeEntity implements Serializable {

    @EmbeddedId
    BadgeKey id;

    private String image;
    private String description;
}
