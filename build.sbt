name := """sandbox-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "io.joshworks.unirest" % "unirest-java" % "1.5.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
