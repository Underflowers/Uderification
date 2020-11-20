package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
    ApplicationEntity findByToken(String token);
}
