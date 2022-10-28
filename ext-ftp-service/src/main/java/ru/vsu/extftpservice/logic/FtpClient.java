package ru.vsu.extftpservice.logic;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FtpClient {

  private final String server;
  private final int port;
  private final String user;
  private final String password;
  private FTPClient ftp;

  FtpClient(String server, int port, String user, String password) {
    this.server = server;
    this.port = port;
    this.user = user;
    this.password = password;
  }

  void open() throws IOException {
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

  void close() throws IOException {
    ftp.disconnect();
  }

  boolean saveFile(String file, String path) throws IOException {
    return ftp.storeFile(path, new ByteArrayInputStream(file.getBytes()));
  }
}
