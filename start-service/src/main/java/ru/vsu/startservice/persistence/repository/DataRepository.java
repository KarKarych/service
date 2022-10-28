package ru.vsu.startservice.persistence.repository;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import ru.vsu.startservice.persistence.model.Data;

import java.util.UUID;

@Repository
public interface DataRepository extends CrudRepository<Data, UUID> {
}
