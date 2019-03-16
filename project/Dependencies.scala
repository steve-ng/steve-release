import sbt._

object Dependencies {
  // Versions
  lazy val typesafeVersion = "1.3.2"

  lazy val guiceVersion = "4.2.2"
  lazy val scalatestVersion = "3.0.5"
  lazy val mockitoCoreVersion = "2.24.0"

  // Common libraries
  val typesafeConfig = "com.typesafe" % "config" % typesafeVersion

  // Test
  val scalaTest = "org.scalatest" %% "scalatest" % scalatestVersion % Test
  val mockitoDependency = "org.mockito" % "mockito-core" % mockitoCoreVersion % Test

  // logstash 4.9 uses same Jackson version (2.6) with Spark 2.4
  // Be careful when upgrading because it breaks compatibility
  val loggingDependencies: Seq[ModuleID] = Seq(
    // For conditional Logback configs
    "org.codehaus.janino" % "janino" % "2.7.8",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "net.logstash.logback" % "logstash-logback-encoder" % "4.9",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
  )

  val commonDeps =
    Seq(
      typesafeConfig,

      // Testing dependencies
      mockitoDependency,
      scalaTest
    ) ++ loggingDependencies

  // Libraries
  val guice = "com.google.inject" % "guice" % guiceVersion

}
