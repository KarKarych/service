package ru.vsu.middleservice.api;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.middleservice.service.logic.DataService;
import ru.vsu.middleservice.service.model.DataDto;

import java.util.List;

@Controller("/data")
@Slf4j
public class DataController {
  @Inject
  private DataService dataService;

  @Post("/data_array")
  public void saveData(List<DataDto> dataDtoList) {
    dataService.saveData(dataDtoList);
  }

  @Post("/data")
  public void saveData(DataDto dataDto) {
    dataService.saveData(dataDto);
  }
}
