package ru.vsu.startservice.service.broker;


import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.startservice.service.logic.BrokerService;
import ru.vsu.startservice.service.model.DataDto;

@KafkaListener
@Slf4j
public class DataListener {
  @Inject
  private BrokerService brokerService;

  @Topic("data") // <4>
  public void updateData(DataDto data) {

    brokerService.saveData(data);
  }
}
