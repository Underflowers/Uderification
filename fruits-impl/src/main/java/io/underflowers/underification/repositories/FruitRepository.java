package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.FruitEntity;
import org.springframework.data.repository.CrudRepository;

public interface FruitRepository extends CrudRepository<FruitEntity, Long> {

}
