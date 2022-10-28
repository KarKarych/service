package ru.vsu.extqueueservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Introspected
public class Data {

  private String title;

  private String description;

  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime date;

  private Priority priority;

  private List<String> arguments;
}
