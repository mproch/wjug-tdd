scalaVersion := "2.10.0"

name := "wjug-tdd"

// Scalaz
libraryDependencies ++= Seq(
  "org.scalaz"     %% "scalaz-core" % "7.0.0"
)

// Shapeless
libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "1.2.4"
)

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.0"
)

scalacOptions += "-feature"

initialCommands in console := "import scalaz._, shapeless._, spire._"
