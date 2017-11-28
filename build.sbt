// *****************************************************************************
// Projects
// *****************************************************************************

lazy val dfasdlCore =
  project
    .in(file("."))
    .enablePlugins(AsciidoctorPlugin, GitBranchPrompt, GitVersioning, GhpagesPlugin)
    .settings(settings)
    .settings(
      name := "dfasdl-core"
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val scalaCheck = "1.13.5"
      val scalaTest  = "3.0.4"
    }
    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"  %% "scalatest"  % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  documentationSettings ++
  gitSettings ++
  publishSettings ++
  resourceFilterSettings

lazy val commonSettings =
  Seq(
    scalaVersion in ThisBuild := "2.12.4",
    crossScalaVersions := Seq("2.12.4", "2.11.12"),
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

lazy val documentationSettings =
  Seq(
    sourceDirectory in Asciidoctor := baseDirectory.value / "doc",
    ghpagesNoJekyll := true,
    git.remoteRepo := "git@github.com:DFASDL/dfasdl-core.git"
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

/*
 * Resource filtering is rather tedious.
 *
 * First we have to exclude the `dfasdl.xsd` from the resources to
 * avoid an error about duplicate dependencies upon packaging.
 * Then we have to parse and filter it according to our needs in the
 * `resourceGenerators` task which is only run upon `package` and `run`.
 *
 * All this using hardcoded filenames. :-(
 */
lazy val resourceFilterSettings =
  Seq(
    excludeFilter in unmanagedResources := "dfasdl.xsd",
    resourceGenerators in Compile += Def.task {
      // Replace the `${version}` token with the current version.
      val s = (resourceDirectory in Compile).value / "org" / "dfasdl" / "dfasdl.xsd"
      val sc = IO.read(s)
      val tc = sc.replaceAll("""\$\{version\}""", version.value)
      val t = (resourceManaged in Compile).value / "org" / "dfasdl" / "dfasdl.xsd"
      IO.write(t, tc)
      Seq(t)
    }.taskValue
  )

