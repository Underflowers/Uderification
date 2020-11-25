package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.PointScaleEntity;
import io.underflowers.underification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRewardRepository extends CrudRepository<PointScaleEntity, Long> {
    List<PointScaleEntity> findAllByUser(UserEntity userEntity);
}