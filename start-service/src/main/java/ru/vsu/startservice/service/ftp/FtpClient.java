package ru.vsu.startservice.service.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FtpClient {

  private final String server;
  private final int port;
  private final String user;
  private final String password;
  private FTPClient ftp;

  public FtpClient(String server, int port, String user, String password) {
    this.server = server;
    this.port = port;
    this.user = user;
    this.password = password;
  }

  public void open() throws IOException {
    ftp = new FTPClient();

    ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

    ftp.connect(server, port);
    int reply = ftp.getReplyCode();

    if (!FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      throw new IOException("Exception in connecting to FTP Server");
    }

    ftp.login(user, password);
    ftp.enterLocalPassiveMode();
  }

  public void close() throws IOException {
    ftp.disconnect();
  }

  public Collection<String> listAllFiles() throws IOException {
    FTPFile[] files = ftp.listFiles("/");

    return Arrays.stream(files)
      .map(FTPFile::getName)
      .filter(name -> name.contains(".json") || name.contains(".txt"))
      .collect(Collectors.toList());
  }

  public String getFileText(String filename) {
    if (filename == null) return null;

    try (InputStream inputStream = ftp.retrieveFileStream(filename)) {
      if (!ftp.completePendingCommand()) return null;

      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      return null;
    }
  }

  public void deleteFiles(List<String> names) {
    names.forEach(pathname -> {
      try {
        ftp.deleteFile(pathname);
      } catch (IOException e) {
        log.warn("Cannot delete file with path %s".formatted(pathname));
      }
    });
  }
}
