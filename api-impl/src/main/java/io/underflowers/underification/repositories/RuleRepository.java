package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RuleRepository extends CrudRepository<RuleEntity, Long> {
    List<RuleEntity> findAllByEventTypeAndApplication(String eventType, ApplicationEntity applicationEntity);
}
