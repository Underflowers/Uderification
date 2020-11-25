package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeEntity;
import io.underflowers.underification.entities.PointRewardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRewardRepository extends CrudRepository<PointRewardEntity, Long> {
    List<PointRewardEntity> findAllByApplication(ApplicationEntity applicationEntity);
}
