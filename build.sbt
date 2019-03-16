import Dependencies._
import CommonSettings._

ThisBuild / name := "tvlk-eci-data-pipeline"
ThisBuild / organization := "com.phoenix"
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
//  checkSnapshotDependencies,              // : ReleaseStep
//  inquireVersions,                        // : ReleaseStep
//  runClean,                               // : ReleaseStep
//  runTest,                                // : ReleaseStep
  releaseStepCommand("assembly")
//  setReleaseVersion,                      // : ReleaseStep
//  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
//  tagRelease,                             // : ReleaseStep
// // publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
//  setNextVersion,                         // : ReleaseStep
//  commitNextVersion,                      // : ReleaseStep
//  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)
