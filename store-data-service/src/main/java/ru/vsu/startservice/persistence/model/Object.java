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
public class Object {

  @Id
  @Column(name = "object_id")
  private String id;

  private String status;

  private String type;

  private String geolocation;

  @Column(name = "change_date")
  private ZonedDateTime changeDate;
}
