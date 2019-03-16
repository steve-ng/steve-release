import Dependencies._
import CommonSettings._

ThisBuild / name := "tvlk-eci-data-pipeline"
ThisBuild / organization := "com.phoenix"
ThisBuild / version := "0.1-SNAPSHOT"
// https://spark.apache.org/docs/latest/ Spark 2.4 works with uses Scala 2.11
ThisBuild / scalaVersion := "2.11.12"

val compileAndTestDependency = "compile->compile;test->test"

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
