package com.daveclay.googledrive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.ArrayList;

public class Search {
  private Drive drive;

  public Search(Drive drive) {
    this.drive = drive;
  }

  public ArrayList<File> byName(String name) throws IOException {
    String pageToken = null;
    ArrayList<File> match = new ArrayList<>();
    do {
      FileList result = drive.files().list()
        .setQ("name='" + name + "'")
        .setSpaces("drive")
        .setFields("nextPageToken, files(id, name, parents)")
        .setPageToken(pageToken)
        .execute();

      for (File file : result.getFiles()) {
        match.add(file);
      }
      pageToken = result.getNextPageToken();
    } while (pageToken != null);
    return match;
  }

  public void exampleSearch() throws IOException {
    FileUtils utils = new FileUtils(drive);
    ArrayList<com.google.api.services.drive.model.File> results = byName("Notes");
    results.forEach(result -> {
      System.out.print(result.getId() + ":\t\t");
      System.out.print(utils.getFullPath(result));
      System.out.println(result.getName());
    });
  }
}
