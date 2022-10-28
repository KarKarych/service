package ru.vsu.extqueueservice.logic;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import ru.vsu.extqueueservice.model.Data;

@KafkaClient
public interface DataClient {
  @Topic("data")
  Data updateData(Data data);
}
