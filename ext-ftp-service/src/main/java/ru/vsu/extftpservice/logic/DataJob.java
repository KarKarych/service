package ru.vsu.extftpservice.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerShutdownEvent;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.vsu.extftpservice.model.Data;
import ru.vsu.extftpservice.model.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

@Singleton
@Slf4j
public class DataJob {
  private FtpClient ftpClient;

  @SneakyThrows
  @EventListener
  void onStartup(ServerStartupEvent event) {
    log.info("Connect to ftp server");
    ftpClient = new FtpClient("johns-last-delight.keenetic.pro", 21, "micronaut", "micronaut");
    ftpClient.open();
  }

  @SneakyThrows
  @EventListener
  void onShutdown(ServerShutdownEvent event) {
    log.info("Disconnect from ftp server");
    ftpClient.close();
  }

  @SneakyThrows
  @Scheduled(fixedDelay = "20s", initialDelay = "5s")
  void updateDataJob() {
    if (ftpClient == null) return;

    Data data = Data.builder()
      .title("test send ftp package")
      .description("test send ftp package")
      .date(LocalDateTime.now())
      .priority(Priority.HIGH)
      .arguments(List.of("1111111111", "1111111"))
      .build();

    ObjectMapper mapper = new ObjectMapper();

    ArrayNode arrayNode = mapper.createArrayNode();
    IntStream.range(0, 5).forEach(n -> {
      data.setTitle(data.getTitle() + n);
      arrayNode.add(mapper.valueToTree(data));
    });

    String logName = "/log-%s.json".formatted(DateTimeFormatter.ofPattern("ddMMyyyyHHmm").format(LocalDateTime.now()));

    log.info("Send data logs to ftp server");
    if (ftpClient.saveFile(mapper.writeValueAsString(arrayNode), logName)) {
      log.info("Successful saving");
    } else {
      log.warn("Failed save");
    }
  }
}
