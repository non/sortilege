name := "sortilege"
organization := "org.spire-math"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
homepage := Some(url("http://github.com/non/sortilege"))

version := "0.4.0"

scalaVersion := "2.12.4"
crossScalaVersions := List("2.10.6", "2.11.12", "2.12.4")

libraryDependencies ++=
  "org.typelevel" %% "spire" % "0.14.1" ::
  "org.scalatest" %% "scalatest" % "3.0.4" % "test" ::
  "org.scalacheck" %% "scalacheck" % "1.13.5" % "test" ::
  Nil

scalacOptions ++=
  "-deprecation" ::
  "-unchecked" ::
  "-feature" ::
  Nil

// release stuff
import ReleaseTransformations._

releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := Function.const(false)

publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging)

pomExtra := (
  <scm>
    <url>git@github.com:non/sortilege.git</url>
    <connection>scm:git:git@github.com:non/sortilege.git</connection>
  </scm>
  <developers>
    <developer>
      <id>d_m</id>
      <name>Erik Osheim</name>
      <url>http://github.com/non/</url>
    </developer>
  </developers>
)

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges)
