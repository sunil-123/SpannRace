name := """spann-race"""

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  // Change this to another test framework if you prefer
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  // Akka
  "com.typesafe.akka" %% "akka-actor" % "2.3.5",
  //"com.typesafe.akka" %% "akka-remote" % "2.3.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.5",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.1.1-2"
)
