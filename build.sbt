name := """order-service"""
organization := "Cloud Kitchens"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

val circeVersion = "0.9.3"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "Cloud Kitchens.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "Cloud Kitchens.binders._"
