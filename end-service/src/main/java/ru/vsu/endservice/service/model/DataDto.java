package ru.vsu.endservice.service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.vsu.endservice.persistence.model.Priority;

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
  private LocalDateTime date;

  private Priority priority;

  private List<String> arguments;
}
