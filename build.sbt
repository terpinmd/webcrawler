lazy val root = (project in file(".")).
  settings(
    name := "webcrawler",
    version := "1.0",
    scalaVersion := "2.11.4",
    libraryDependencies += "org.apache.derby" % "derby" % "10.4.1.3",
    libraryDependencies +=  "org.scalaj" % "scalaj-http_2.11" % "1.1.4",
    libraryDependencies += "net.liftweb" %% "lift-json" % "2.6",
    libraryDependencies += "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.6.1",
    libraryDependencies += "commons-lang" % "commons-lang" % "2.3",
    scalaSource in Compile := baseDirectory.value / "src"
  )
