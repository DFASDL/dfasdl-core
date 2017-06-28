// *****************************************************************************
// Projects
// *****************************************************************************

import sbtfilter.Plugin.FilterKeys._

lazy val dfasdlCore =
  project
    .in(file("."))
    .enablePlugins(AsciidoctorPlugin, GitBranchPrompt, GitVersioning, GhpagesPlugin)
    .settings(settings)
    .settings(filterSettings)
    .settings(
      name := "dfasdl-core",
      sourceDirectory in Asciidoctor := baseDirectory.value / "doc",
      includeFilter in (Compile, filterResources) ~= { f => f || ("*.props" | "*.conf" | "*.properties" | "*.xml" | "*.xsd") },
      (packageBin in Compile) := ((packageBin in Compile) dependsOn (filterResources in Compile)).value,
      ghpagesNoJekyll := true,
      git.remoteRepo := "git@github.com:DFASDL/dfasdl-core.git"
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val scalaCheck = "1.13.5"
      val scalaTest  = "3.0.3"
    }
    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"  %% "scalatest"  % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  gitSettings ++
  publishSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.2",
    crossScalaVersions := Seq("2.11.11", scalaVersion.value),
    organization := "org.dfasdl",
    organizationName := "Wegtam GmbH",
    startYear := Option(2014),
    licenses += ("MPL-2.0", url("https://www.mozilla.org/en-US/MPL/2.0/")),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-language:_",
      "-target:jvm-1.8",
      "-unchecked",
      "-Xfatal-warnings",
      "-Xfuture",
      "-Xlint",
      "-Ydelambdafy:method",
      "-Yno-adapted-args",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused-import",
      "-Ywarn-value-discard"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value)
)

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

lazy val publishSettings =
  Seq(
    bintrayOrganization := Option("wegtam"),
    bintrayPackage := "dfasdl-core",
    bintrayReleaseOnPublish in ThisBuild := false,
    bintrayRepository := "dfasdl",
    developers += Developer(
      "wegtam",
      "Wegtam GmbH",
      "tech@wegtam.com",
      url("https://www.wegtam.com")
    ),
    homepage := Option(url("https://github.com/DFASDL/dfasdl-core")),
    pomIncludeRepository := (_ => false),
    scmInfo := Option(ScmInfo(
      url("https://github.com/DFASDL/dfasdl-core"),
      "git@github.com:DFASDL/dfasdl-core.git"
    ))
  )

