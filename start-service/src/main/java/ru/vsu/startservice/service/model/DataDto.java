package ru.vsu.startservice.service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.micronaut.core.annotation.Introspected;
import lombok.*;
import ru.vsu.startservice.persistence.model.Priority;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Introspected
public class DataDto {

  private String title;

  private String description;

  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime date;

  private Priority priority;

  private List<String> arguments;
}
