addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.1")
addSbtPlugin("com.typesafe.sbt"  % "sbt-ghpages" % "0.6.2")
addSbtPlugin("com.typesafe.sbt"  % "sbt-git"     % "0.9.3")
addSbtPlugin("com.jsuereth"      % "sbt-pgp"     % "1.1.0")
addSbtPlugin("com.typesafe.sbt"  % "sbt-site"    % "1.3.1")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25" // Needed by sbt-git

