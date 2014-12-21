name := """ww-ceem-radar"""

version := "1.0"

scalaVersion := "2.11.4"

javacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

libraryDependencies += "com.h2database" % "h2" % "1.3.148"

libraryDependencies += "com.intellij" % "forms_rt" % "7.0.3"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.7"

libraryDependencies += "com.typesafe.slick" % "slick_2.11" % "2.1.0"

libraryDependencies += "com.google.guava" % "guava" % "18.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"

//unmanagedJars in Compile ++= Seq(
//  file("lib/serious-panda-settings-assembly-0.3.jar"),
//  file("lib/gdal.jar"),
//  file("lib/gluegen-rt.jar"),
//  file("lib/gluegen-rt-natives-linux-amd64.jar"),
//  file("lib/gluegen-rt-natives-linux-i586.jar"),
//  file("lib/gluegen-rt-natives-linux-i586.jar"),
//  file("lib/gluegen-rt-natives-linux-i586.jar"),
//  file("lib/gluegen-rt-natives-linux-i586.jar"),
//  file("lib/gluegen-rt-natives-linux-i586.jar"),
//  file("lib/gluegen-rt-natives-linux-i586.jar"),
//
//)

unmanagedBase := baseDirectory.value / "lib"