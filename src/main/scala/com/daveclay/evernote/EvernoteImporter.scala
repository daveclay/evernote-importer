package com.daveclay.evernote

import java.io.{File, FileInputStream}
import com.daveclay.googledrive.{DriveServiceFactory, Uploader}

object EvernoteImporter extends App {
  // Build a new authorized API client service.
  if (args.length != 2) {
    System.err.println("[creds] [xml]")
    System.exit(1)
  }

  val clientSecretsFile = new File(args(0))
  if (!clientSecretsFile.exists()) {
    System.err.println("File does not exist: " + args(0))
    System.exit(2)
  }

  val clientSecretsStream = new FileInputStream(clientSecretsFile)
  val drive = DriveServiceFactory.getDriveService(clientSecretsStream)

  val xmlFile = new File(args(1))
  if (!xmlFile.exists()) {
    System.err.println("File does not exist: " + args(1))
    System.exit(2)
  }

  val notes = new EvernoteParser().parse(xmlFile)
  notes.foreach( note => {
    val uploader = new Uploader(drive)
    uploader.uploadHtml(note.title, "0B-CPsBp6Ok8JR0NSeHI0NmVPSVk", note.content)
  })
}


