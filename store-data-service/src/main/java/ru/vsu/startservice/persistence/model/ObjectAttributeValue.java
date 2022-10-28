package ru.vsu.startservice.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
public class ObjectAttributeValue {

  @Id
  @Column(name = "object_id")
  private String id;

  @Column(name = "attribute_id")
  private String attributeId;

  @Column(name = "source_id")
  private String sourceId;

  @Column(columnDefinition = "json")
  private String value;

  @Column(name = "change_date")
  private ZonedDateTime changeDate;
}
