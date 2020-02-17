addSbtPlugin("org.foundweekends" % "sbt-bintray"     % "0.5.1")
addSbtPlugin("com.typesafe.sbt"  % "sbt-ghpages"     % "0.6.2")
addSbtPlugin("com.typesafe.sbt"  % "sbt-git"         % "1.0.0")
addSbtPlugin("com.jsuereth"      % "sbt-pgp"         % "2.0.1")
addSbtPlugin("org.scalameta"     % "sbt-scalafmt"    % "2.3.1")
addSbtPlugin("org.scoverage"     % "sbt-scoverage"   % "1.6.1")
addSbtPlugin("com.typesafe.sbt"  % "sbt-site"        % "1.3.1")
addSbtPlugin("org.wartremover"   % "sbt-wartremover" % "2.4.3")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25" // Needed by sbt-git

