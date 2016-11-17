
organization in ThisBuild := "io.strongtyped"

scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf"))


// dependencies
lazy val root = Project(
    id   = "fun-cqrs-play-json-root",
    base = file("."),
    settings = Seq(
      publishArtifact := false
    )
  ) aggregate (
    funPlayJsonSupport,
    funPlay25JsonSupport
  )

lazy val defaultSettings = Seq(
  scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-Xlint:-infer-any", "-Xfatal-warnings"),
  scalaVersion in ThisBuild := "2.11.8"
) 

val funCQRS = "io.strongtyped" %% "fun-cqrs-core" % "0.4.8"

// Play24 Json support ============================
lazy val funPlayJsonSupport = {

  //set source dir to source dir in commonPlayModule
  val sourceDir = (baseDirectory in ThisBuild)(b => Seq(b / "modules/play-json/src/main/scala"))
  val play24Json =    "com.typesafe.play"          %% "play-json"          %  "2.4.8"

  Project(
    id       = "fun-cqrs-play-json",
    base     = file("modules/play24-json"),
    settings = defaultSettings
  ).settings(libraryDependencies ++= Seq(funCQRS, play24Json))
   .settings(unmanagedSourceDirectories in Compile <<= sourceDir)
}
//================================================


// Play25 Json support ==========================
lazy val funPlay25JsonSupport = {

  //set source dir to source dir in commonPlayModule
  val sourceDir = (baseDirectory in ThisBuild)(b => Seq(b / "modules/play-json/src/main/scala"))
  val play25Json =    "com.typesafe.play"          %% "play-json"          %  "2.5.9"

  Project(
    id       = "fun-cqrs-play25-json",
    base     = file("modules/play25-json"),
    settings = defaultSettings
  ).settings(libraryDependencies ++= Seq(funCQRS, play25Json))
   .settings(unmanagedSourceDirectories in Compile <<= sourceDir)
}
//================================================
