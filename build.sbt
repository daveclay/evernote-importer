name := "evernote-importer"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
                             "com.google.api-client" % "google-api-client" % "1.23.0",
                             "com.google.oauth-client" % "google-oauth-client-jetty" % "1.23.0",
                             "com.google.apis" % "google-api-services-drive" % "v3-rev85-1.23.0",
                             "com.fasterxml" % "jackson-xml-databind" % "0.6.2",
                             "com.fasterxml.woodstox" % "woodstox-core" % "5.0.3",
                             "com.lucidchart" % "xtract_2.12" % "1.3.1",
)
