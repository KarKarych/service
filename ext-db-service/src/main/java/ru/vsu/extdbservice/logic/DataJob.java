package ru.vsu.extdbservice.logic;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.extdbservice.persistence.model.Argument;
import ru.vsu.extdbservice.persistence.model.Data;
import ru.vsu.extdbservice.persistence.model.Priority;
import ru.vsu.extdbservice.persistence.repository.ArgumentRepository;
import ru.vsu.extdbservice.persistence.repository.DataRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@Singleton
@Slf4j
public class DataJob {
  @Inject
  private DataRepository dataRepository;

  @Inject
  private ArgumentRepository argumentRepository;

  @SneakyThrows
  @Scheduled(fixedDelay = "20s", initialDelay = "5s")
  void updateDataJob() {

    Data data = new Data();
    data.setTitle("test db package");
    data.setDescription("test db package");
    data.setDate(LocalDateTime.now());
    data.setPriority(Priority.HIGH);

    log.info("Saving data");
    Data savedData = dataRepository.save(data);

    IntStream.range(0, 5).forEach(n -> {
      Argument argument = new Argument();
      argument.setArgument("test" + n);
      argument.setData(savedData);
      argumentRepository.save(argument);
    });

    Optional<Data> optionalData = dataRepository.findById(savedData.getId());

    if (optionalData.isPresent()) {
      log.info("Successful saving " + optionalData.get());
    } else {
      log.warn("Failed save");
    }
  }
}
