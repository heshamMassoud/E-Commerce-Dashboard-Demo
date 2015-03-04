name := """Dashboard-Demo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "io.sphere" % "sphere-play-sdk_2.10" % "0.67.0" withSources()
)

