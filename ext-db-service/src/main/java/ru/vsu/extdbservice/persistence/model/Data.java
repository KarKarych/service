package ru.vsu.extdbservice.persistence.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
public class Data {

  @Id
  @GeneratedValue
  private UUID id;

  private String title;

  private String description;

  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime date;

  @Enumerated(EnumType.STRING)
  private Priority priority;

  @OneToMany(mappedBy="data", orphanRemoval = true)
  private List<Argument> arguments;
}
