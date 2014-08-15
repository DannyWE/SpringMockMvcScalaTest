name := "SpringMockMvcScalaTest"

scalaVersion := "2.10.4"

version := "1.0"

libraryDependencies ++= Seq(
"org.springframework" % "spring-test" % "4.0.6.RELEASE",
"org.springframework" % "spring-context" % "4.0.6.RELEASE",
"org.springframework" % "spring-web" % "4.0.6.RELEASE",
"org.springframework" % "spring-webmvc" % "4.0.6.RELEASE",
  "commons-io" % "commons-io" % "1.3.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.3",
  "javax.servlet" % "javax.servlet-api" % "3.0.1",
"org.specs2" % "specs2_2.10" % "2.3.13",
"org.scalacheck" % "scalacheck_2.10" % "1.11.5"
)

