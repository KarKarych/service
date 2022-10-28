package ru.vsu.extqueueservice.logic;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.extqueueservice.model.Data;
import ru.vsu.extqueueservice.model.Priority;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Slf4j
public class DataJob {
  private final DataClient dataClient;

  public DataJob(DataClient dataClient) { // <3>
    this.dataClient = dataClient;
  }

  @Scheduled(fixedDelay = "6s")
  void updateDataJob() {
    log.info("Send data package to kafka query");

    Data data = Data.builder()
      .title("test send package")
      .description("test send package")
      .date(LocalDateTime.now())
      .priority(Priority.HIGH)
      .arguments(List.of("123", "1232"))
      .build();

    log.info(dataClient.updateData(data).toString());
  }
}
