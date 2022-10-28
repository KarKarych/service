package ru.vsu.endservice.service.logic;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import ru.vsu.endservice.service.model.DataDto;

@KafkaClient
public interface DataClient {

  @Topic("data")
  void updateData(DataDto dataDtos);
}
