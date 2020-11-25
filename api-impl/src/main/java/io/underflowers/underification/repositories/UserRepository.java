package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByAppUserIdAndApplication(String appUserId, ApplicationEntity applicationEntity);
}
