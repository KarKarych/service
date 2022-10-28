package ru.vsu.startservice.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AttributeSource {

  @Id
  @Column(name = "source_id")
  private String id;

  @Column(name = "attribute_id")
  private String attributeId;

  @Column(name = "external_attribute_id")
  private String externalAttributeId;

  @Enumerated(EnumType.STRING)
  private Priority priority;
}
