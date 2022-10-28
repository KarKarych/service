package ru.vsu.startservice.controller.api;

import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import ru.vsu.startservice.service.model.DataDto;

import java.util.List;

@Client(id = "middle-service", path = "/data")
public interface DataClient {
  @Post("/data_array")
  void saveData(List<DataDto> dataDtoList);
  @Post("/data")
  void saveData(DataDto dataDto);
}
