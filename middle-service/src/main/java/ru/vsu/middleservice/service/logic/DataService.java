package ru.vsu.middleservice.service.logic;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.middleservice.persistence.model.Argument;
import ru.vsu.middleservice.persistence.model.Data;
import ru.vsu.middleservice.persistence.repository.ArgumentRepository;
import ru.vsu.middleservice.persistence.repository.DataRepository;
import ru.vsu.middleservice.service.model.DataDto;

import java.util.List;
import java.util.Optional;

@Singleton
@Slf4j
public class DataService {
  @Inject
  private DataRepository dataRepository;
  @Inject
  private ArgumentRepository argumentRepository;

  public void saveData(List<DataDto> dataDtoList) {
    dataDtoList.forEach(this::saveData);
  }
  public void saveData(DataDto dataDto) {
    Data data = new Data();
    data.setTitle(dataDto.getTitle());
    data.setDescription(dataDto.getDescription());
    data.setDate(dataDto.getDate());
    data.setPriority(dataDto.getPriority());

    Data savedData = dataRepository.save(data);

    dataDto.getArguments().forEach(a -> {
      Argument argument = new Argument();
      argument.setArgument(a);
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
