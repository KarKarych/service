package ru.vsu.endservice.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Argument {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(length = 1024)
  private String argument;

  @ManyToOne
  @JoinColumn(name="data_id", nullable=false)
  private Data data;

  @Override
  public String toString() {
    return argument;
  }
}
