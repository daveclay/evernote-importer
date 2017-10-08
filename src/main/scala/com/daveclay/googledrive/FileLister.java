package com.daveclay.googledrive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.*;
import java.util.List;

public class FileLister {

  private Drive drive;

  public FileLister(Drive drive) {
    this.drive = drive;
  }

  public void go() throws IOException {
    // Print the names and IDs for up to 10 files.
    FileList result = drive.files().list()
      .setPageSize(10)
      .setFields("nextPageToken, files(id, name)")
      .execute();
    List<File> files = result.getFiles();
    if (files == null || files.size() == 0) {
      System.out.println("No files found.");
    } else {
      System.out.println("Files:");
      for (File file : files) {
        System.out.printf("%s (%s)\n", file.getName(), file.getId());
      }
    }

  }
}
