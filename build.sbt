name := """ClearScore"""
organization := "com.clearscore"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin)

//scalaVersion := "2.13.8"
scalaVersion := "2.12.15"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.clearscore.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.clearscore.binders._"

swaggerDomainNameSpaces := Seq("models")

libraryDependencies += "org.webjars" % "swagger-ui" % "3.43.0"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2"
