package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.BadgeRewardEntity;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRewardRepository extends CrudRepository<BadgeRewardEntity, Long> {
}