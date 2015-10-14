name := "sortilege"

organization := "us.mocul"

version := "0.3.0"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.5", "2.11.7")

libraryDependencies ++= Seq(
  "org.spire-math" %% "spire" % "0.10.1",
  "org.scalatest" %% "scalatest" % "3.0.0-M7" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

homepage := Some(url("http://github.com/non/sortilege"))
