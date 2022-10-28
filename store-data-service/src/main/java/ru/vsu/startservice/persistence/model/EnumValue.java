package ru.vsu.startservice.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class EnumValue {

  @Id
  @Column(name = "attribute_id")
  private String id;

  @Column(name = "enum_id")
  private String enumId;

  @Column(columnDefinition = "json")
  private String value;
}
