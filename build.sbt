name := """msmp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

resolvers +=
  "mvnrepo" at "http://mvnrepository.com/artifact/"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.4.1208.jre7"
)


fork in run := true