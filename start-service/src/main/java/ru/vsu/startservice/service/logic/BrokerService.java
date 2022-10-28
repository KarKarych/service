package ru.vsu.startservice.service.logic;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.startservice.controller.api.DataClient;
import ru.vsu.startservice.service.model.DataDto;

@Singleton
@Slf4j
public class BrokerService {
  @Inject
  private DataClient dataClient;

  public void saveData(DataDto dataDto){
    log.info("Send data from kafka");
    dataClient.saveData(dataDto);
  }
}
