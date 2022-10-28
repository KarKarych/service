package ru.vsu.startservice.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Attribute {

  @Id
  @Column(name = "attribute_id")
  private String id;

  private String description;

  private String type;
}
