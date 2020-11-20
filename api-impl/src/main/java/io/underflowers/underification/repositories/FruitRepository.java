package io.underflowers.underification.repositories;

import io.underflowers.underification.entities.FruitEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;

public interface FruitRepository extends CrudRepository<FruitEntity, Long> {

}
