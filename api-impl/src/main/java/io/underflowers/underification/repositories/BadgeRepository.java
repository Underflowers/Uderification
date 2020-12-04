package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeEntity;
import io.underflowers.underification.entities.keys.BadgeKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository extends CrudRepository<BadgeEntity, Long> {
    List<BadgeEntity> findAllByApplication(ApplicationEntity applicationEntity);
    BadgeEntity findByNameAndApplication(String name, ApplicationEntity applicationEntity);
}
