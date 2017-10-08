package com.daveclay.googledrive;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.*;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Uploader {
  private Drive drive;

  public Uploader(Drive drive) {
    this.drive = drive;
  }

  class CustomProgressListener implements MediaHttpUploaderProgressListener {
    public void progressChanged(MediaHttpUploader uploader) throws IOException {
      switch (uploader.getUploadState()) {
        case INITIATION_STARTED:
          System.out.println("Initiation has started!");
          break;
        case INITIATION_COMPLETE:
          System.out.println("Initiation is complete!");
          break;
        case MEDIA_IN_PROGRESS:
          System.out.println(uploader.getProgress());
          break;
        case MEDIA_COMPLETE:
          System.out.println("Upload is complete!");
      }
    }
  }

  public void upload(String name, String parentId, java.io.File content) throws IOException {
    InputStreamContent mediaContent = new InputStreamContent("text/plain",
      new BufferedInputStream(new FileInputStream(content)));
    mediaContent.setLength(content.length());
    upload(name, parentId, mediaContent);
  }

  public void uploadHtml(String name, String parentId, String content) throws IOException {
    ByteArrayContent mediaContent = new ByteArrayContent("text/html", content.getBytes());
    upload(name, parentId, mediaContent);
  }

  public void upload(String name, String parentId, AbstractInputStreamContent mediaContent) throws IOException {
    File file = new File();
    file.setName(name);
    file.setParents(Collections.singletonList(parentId));
    file.setMimeType("application/vnd.google-apps.document");

    Drive.Files.Create request = drive.files().create(file, mediaContent);
    request.getMediaHttpUploader().setProgressListener(new CustomProgressListener());
    request.execute();
  }

}
