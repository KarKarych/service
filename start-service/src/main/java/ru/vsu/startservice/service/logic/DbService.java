package ru.vsu.startservice.service.logic;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.startservice.controller.api.DataClient;
import ru.vsu.startservice.persistence.model.Argument;
import ru.vsu.startservice.persistence.model.Data;
import ru.vsu.startservice.persistence.repository.DataRepository;
import ru.vsu.startservice.service.model.DataDto;

import java.util.List;
import java.util.stream.StreamSupport;

@Singleton
@Slf4j
public class DbService {

  @Inject
  private DataRepository dataRepository;
  @Inject
  private DataClient dataClient;

  @Scheduled(fixedDelay = "6s")
  public void getDataFromDb() {
    Iterable<Data> allData = dataRepository.findAll();

    List<DataDto> dataDtos = StreamSupport.stream(allData.spliterator(), false)
      .toList().stream()
      .map(DbService::fromEntity).toList();

    if (dataDtos.size() == 0) {
      log.info("Nothing to send. DB is empty");
      return;
    }

    dataRepository.deleteAll(allData);

    log.info("Send data from db");

    dataClient.saveData(dataDtos);
  }

  private static DataDto fromEntity(Data data) {
    if (data == null) return null;

    DataDto dataDto = new DataDto();

    dataDto.setTitle(data.getTitle());
    dataDto.setDescription(data.getDescription());
    dataDto.setDate(data.getDate());
    dataDto.setPriority(data.getPriority());
    dataDto.setArguments(data.getArguments().stream().map(Argument::getArgument).toList());

    return dataDto;
  }
}
