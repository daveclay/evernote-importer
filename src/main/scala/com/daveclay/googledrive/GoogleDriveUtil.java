package com.daveclay.googledrive;

import com.google.api.services.drive.Drive;

import java.io.*;
import java.io.File;

public class GoogleDriveUtil {

  public static void main(String[] args) throws IOException {
    // Build a new authorized API client service.
    if (args.length != 1) {
      System.err.println("gimme a creds file.");
      System.exit(1);
    }

    File clientSecretsFile = new File(args[0]);
    if (!clientSecretsFile.exists()) {
      System.err.println("File does not exist: " + args[0]);
      System.exit(2);
    }

    InputStream clientSecretsStream = new FileInputStream(clientSecretsFile);
    Drive drive = DriveServiceFactory.getDriveService(clientSecretsStream);

    File up = new File("blah.txt");
    Uploader uploader = new Uploader(drive);
    uploader.upload("Test", "0B-CPsBp6Ok8JR0NSeHI0NmVPSVk", up);
  }

}

