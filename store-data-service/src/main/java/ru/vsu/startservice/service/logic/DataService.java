package ru.vsu.startservice.service.logic;

import ru.vsu.startservice.persistence.repository.*;
import ru.vsu.startservice.service.broker.client.TopicClient;
import ru.vsu.startservice.service.mapper.DataMapper;
import ru.vsu.startservice.service.model.DataDto;

public class DataService {

  private DataMapper dataMapper;

  private AttributeRepository attributeRepository;
  private AttributeSourceRepository attributeSourceRepository;
  private EnumValueRepository enumValueRepository;
  private ObjectAttributeRepository objectAttributeRepository;
  private ObjectRepository objectRepository;

  private void saveOrUpdate(DataDto dataDto){

  }

  private TopicClient topicClient;
}
