package ru.vsu.endservice.persistence.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import ru.vsu.endservice.persistence.model.Argument;

import java.util.UUID;

@Repository
public interface ArgumentRepository extends CrudRepository<Argument, UUID> {
}
