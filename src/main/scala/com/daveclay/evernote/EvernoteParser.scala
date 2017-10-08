package com.daveclay.evernote

import java.io.File
import java.text.SimpleDateFormat

import scala.xml.XML

class EvernoteParser {

  def parse(file: File) = {
    val sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'")

    val xml = XML.loadFile(file)

    val noteElements = xml \\ "note"

    noteElements.map { note =>
      val title = (note \ "title").text
      val content = (note \ "content").text
      val createdAt = sdf.parse((note \ "created").text)

      Note(title, content, createdAt)
    }
  }
}
