package ru.vsu.startservice.service.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerShutdownEvent;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.startservice.controller.api.DataClient;
import ru.vsu.startservice.service.ftp.FtpClient;
import ru.vsu.startservice.service.model.DataDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Singleton
@Slf4j
public class FtpService {

  @Inject
  private DataClient dataClient;

  private FtpClient ftpClient;

  @SneakyThrows
  @EventListener
  public void onStartup(ServerStartupEvent event) {
    log.info("Connect to ftp server");
    ftpClient = new FtpClient("johns-last-delight.keenetic.pro", 21, "micronaut", "micronaut");
    ftpClient.open();
  }

  @SneakyThrows
  @EventListener
  public void onShutdown(ServerShutdownEvent event) {
    log.info("Disconnect from ftp server");
    ftpClient.close();
  }

  @Scheduled(fixedDelay = "6s")
  @SneakyThrows
  public void getDataFromFtp() {
    if (ftpClient == null) return;

    Collection<String> fileNames = ftpClient.listAllFiles();

    List<DataDto> allData = fileNames.stream()
      .map(ftpClient::getFileText)
      .map(FtpService::fromJSON)
      .filter(Objects::nonNull)
      .flatMap(Collection::stream)
      .toList();

    if (allData.size() == 0) {
      log.info("Nothing to send. Ftp server is empty");
      return;
    }

    ftpClient.deleteFiles(List.copyOf(fileNames));

    log.info("Send data from ftp");

    dataClient.saveData(allData);
  }

  private static List<DataDto> fromJSON(final String jsonPacket) {
    if (jsonPacket == null) return null;

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    try {
      return Arrays.asList(objectMapper.readValue(jsonPacket, DataDto[].class));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
