package com.daveclay.googledrive;

import com.google.api.services.drive.Drive;

import java.io.IOException;
import java.util.List;

public class FileUtils {
  private Drive drive;

  public FileUtils(Drive drive) {
    this.drive = drive;
  }

  public String getFullPath(com.google.api.services.drive.model.File file) {
    List<String> parents = file.getParents();
    return parents.stream().reduce("", (s, fileId) -> {
      com.google.api.services.drive.model.File parent = null;
      try {
        parent = drive.files().get(fileId).execute();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
      return s += parent.getName() + "/";
    });
  }
}
