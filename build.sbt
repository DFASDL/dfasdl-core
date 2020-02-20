// *****************************************************************************
// Projects
// *****************************************************************************

lazy val dfasdlCore =
  project
    .in(file("."))
    .enablePlugins(AsciidoctorPlugin, GitBranchPrompt, GitVersioning, GhpagesPlugin)
    .settings(settings)
    .settings(
      name := "dfasdl-core",
      libraryDependencies ++= Seq(
        library.enumeratumCore,
        library.refinedCore,
        library.refinedScalaCheck % Test,
        library.scalaCheck        % Test,
        library.scalaTest         % Test
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val enumeratum = "1.5.15"
      val refined    = "0.9.12"
      val scalaCheck = "1.14.3"
      val scalaTest  = "3.0.8"
    }
    val enumeratumCore    = "com.beachape"   %% "enumeratum"         % Version.enumeratum
    val refinedCore       = "eu.timepit"     %% "refined"            % Version.refined
    val refinedScalaCheck = "eu.timepit"     %% "refined-scalacheck" % Version.refined
    val scalaCheck        = "org.scalacheck" %% "scalacheck"         % Version.scalaCheck
    val scalaTest         = "org.scalatest"  %% "scalatest"          % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  documentationSettings ++
  gitSettings ++
  publishSettings ++
  resourceFilterSettings ++
  scalafmtSettings

def compilerSettings(sv: String) =
  CrossVersion.partialVersion(sv) match {
    case Some((2, 11)) =>
      Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-explaintypes",
      "-feature",
      "-language:higherKinds",
      "-target:jvm-1.8",
      "-unchecked",
      "-Xcheckinit",
      "-Xfatal-warnings",
      "-Xfuture",
      "-Xlint:adapted-args",
      "-Xlint:by-name-right-associative",
      "-Xlint:delayedinit-select",
      "-Xlint:doc-detached",
      "-Xlint:inaccessible",
      "-Xlint:infer-any",
      "-Xlint:missing-interpolator",
      "-Xlint:nullary-override",
      "-Xlint:nullary-unit",
      "-Xlint:option-implicit",
      "-Xlint:package-object-classes",
      "-Xlint:poly-implicit-overload",
      "-Xlint:private-shadow",
      "-Xlint:stars-align",
      "-Xlint:type-parameter-shadow",
      "-Xlint:unsound-match",
      "-Ydelambdafy:method",
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused-import",
      "-Ywarn-value-discard"
    )
    case Some((2, 12)) =>
      Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-explaintypes",
      "-feature",
      "-language:higherKinds",
      "-target:jvm-1.8",
      "-unchecked",
      "-Xcheckinit",
      "-Xfatal-warnings",
      "-Xfuture",
      "-Xlint:adapted-args",
      "-Xlint:by-name-right-associative",
      "-Xlint:constant",
      "-Xlint:delayedinit-select",
      "-Xlint:doc-detached",
      "-Xlint:inaccessible",
      "-Xlint:infer-any",
      "-Xlint:missing-interpolator",
      "-Xlint:nullary-override",
      "-Xlint:nullary-unit",
      "-Xlint:option-implicit",
      "-Xlint:package-object-classes",
      "-Xlint:poly-implicit-overload",
      "-Xlint:private-shadow",
      "-Xlint:stars-align",
      "-Xlint:type-parameter-shadow",
      "-Xlint:unsound-match",
      "-Ydelambdafy:method",
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused-import",
      "-Ywarn-value-discard"
    )
    case _ =>
      Seq(
	"-deprecation",
	"-explaintypes",
	"-feature",
	"-language:higherKinds",
	"-unchecked",
	"-Xcheckinit",
	"-Xfatal-warnings",
	"-Xlint:adapted-args",
	"-Xlint:constant",
	"-Xlint:delayedinit-select",
	"-Xlint:doc-detached",
	"-Xlint:inaccessible",
	"-Xlint:infer-any",
	"-Xlint:missing-interpolator",
	"-Xlint:nullary-override",
	"-Xlint:nullary-unit",
	"-Xlint:option-implicit",
	"-Xlint:package-object-classes",
	"-Xlint:poly-implicit-overload",
	"-Xlint:private-shadow",
	"-Xlint:stars-align",
	"-Xlint:type-parameter-shadow",
	"-Ywarn-dead-code",
	"-Ywarn-extra-implicit",
	"-Ywarn-numeric-widen",
	"-Ywarn-unused:implicits",
	"-Ywarn-unused:imports",
	"-Ywarn-unused:locals",
	"-Ywarn-unused:params",
	"-Ywarn-unused:patvars",
	"-Ywarn-unused:privates",
	"-Ywarn-value-discard",
	"-Ycache-plugin-class-loader:last-modified",
	"-Ycache-macro-class-loader:last-modified",
      )
  }

lazy val commonSettings =
  Seq(
    scalaVersion in ThisBuild := "2.13.1",
    crossScalaVersions := Seq(scalaVersion.value, "2.12.10", "2.11.12"),
    organization := "org.dfasdl",
    organizationName := "Wegtam GmbH",
    startYear := Option(2014),
    licenses += ("MPL-2.0", url("https://www.mozilla.org/en-US/MPL/2.0/")),
    scalacOptions ++= compilerSettings(scalaVersion.value),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
    wartremoverWarnings in (Compile, compile) ++= Warts.unsafe
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

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
  )
