name := "amqp"

liftVersion <<= liftVersion ?? "2.4"

version <<= liftVersion apply { _ + "-1.0-SNAPSHOT" }

organization := "net.liftmodules"
 
scalaVersion := "2.9.1"
 
crossScalaVersions := Seq("2.8.1", "2.9.0-1", "2.9.1")

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

libraryDependencies <++= liftVersion { v =>
  "net.liftweb" %% "lift-webkit" % v % "compile->default" ::
  "net.liftweb" %% "lift-actor" % v % "compile->default" ::
  Nil
}    

libraryDependencies ++= Seq(
  "org.scala-tools.testing" % "specs_2.9.1" % "1.6.9" % "test", 
  "org.scala-tools.testing" % "scalacheck_2.9.1" % "1.9" % "test",
  "com.rabbitmq" % "amqp-client" % "1.7.2"
)

publishTo <<= version { _.endsWith("SNAPSHOT") match {
 	case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
 	case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
 } 


// For local deployment:

credentials += Credentials( file("sonatype.credentials") )

// For the build server:

credentials += Credentials( file("/private/liftmodules/sonatype.credentials") )

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }


pomExtra := (
	<url>https://github.com/liftmodules/amqp</url>
	<licenses>
		<license>
	      <name>Apache 2.0 License</name>
	      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
	      <distribution>repo</distribution>
	    </license>
	 </licenses>
	 <scm>
	    <url>git@github.com:liftmodules/amqp.git</url>
	    <connection>scm:git:git@github.com:liftmodules/amqp.git</connection>
	 </scm>
	 <developers>
	    <developer>
	      <id>liftmodules</id>
	      <name>Lift Team</name>
	      <url>http://www.liftmodules.net</url>
	 	</developer>
	 </developers> 
 )
  