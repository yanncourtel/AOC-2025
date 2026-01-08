name := "santa-gift-dispatcher"

version := "1.0"

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")
