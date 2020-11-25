package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByAppUserId(String appUserId);
}
