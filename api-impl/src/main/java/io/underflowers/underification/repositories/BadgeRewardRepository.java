package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeEntity;
import io.underflowers.underification.entities.BadgeRewardEntity;
import io.underflowers.underification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRewardRepository extends CrudRepository<BadgeRewardEntity, Long> {
    List<BadgeRewardEntity> findAllByUser(UserEntity user);
}