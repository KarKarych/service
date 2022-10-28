package ru.vsu.startservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;
import java.util.Optional;

public enum Priority {

  HIGH("high"),
  MEDIUM("medium"),
  LOW("low");

  private final String name;

  Priority(String name) {
    this.name = name;
  }

  public static Optional<Priority> fromName(String name) {
    if (name == null) {
      return Optional.empty();
    }

    for (var value : Priority.values()) {
      if (Objects.equals(value.name, name)) {
        return Optional.of(value);
      }
    }

    return Optional.empty();
  }

  @JsonValue
  public String getName() {
    return name;
  }
}
