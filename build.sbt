import Dependencies._
import CommonSettings._

ThisBuild / name := "steve-release"
ThisBuild / organization := "com.steve"
ThisBuild / version := "0.1-SNAPSHOT"
// https://spark.apache.org/docs/latest/ Spark 2.4 works with uses Scala 2.11
ThisBuild / scalaVersion := "2.11.12"

val compileAndTestDependency = "compile->compile;test->test"

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.withClassifier(Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

lazy val common = project
  .settings(
    name := "common",
    commonSettings,
    libraryDependencies ++= commonDeps
  )

// flattener spark job
lazy val flattener = project
  .settings(
    name := "flattener",
    commonSettings,
    libraryDependencies += guice
  )
  .dependsOn(common % compileAndTestDependency)

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runClean,                               // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  releaseStepCommand("assembly")
)