# SESHAT

[![javadoc](https://javadoc.io/badge2/science.aist.seshat/seshat/javadoc.svg)](https://javadoc.io/doc/science.aist.seshat/seshat) [![Maven Central](https://img.shields.io/maven-central/v/science.aist.seshat/seshat.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22science.aist.seshat%22%20AND%20a:%22seshat%22) [![GitHub release](https://img.shields.io/github/v/release/fhooeaist/seshat.svg)](https://github.com/fhooeaist/seshat/releases) [![License: MPL 2.0](https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg)](https://opensource.org/licenses/MPL-2.0) [![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FFHOOEAIST%2Fseshat.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FFHOOEAIST%2Fseshat?ref=badge_shield)

Seshat is a logging framework with the following core considerations:

- High performance / low overhead for logging
- Providing a uniform interface enabling dynamic interchange of existing logger frameworks

## Getting Started

The base module is the api- module which has to be included wherever the logger wants to be used. All other modules 
contain a factory as well a specific logger, that are loaded during runtime. This means, that at compile-time only the 
api module needs to be present.

To use it via Maven you have to include the api dependency.

```xml
<dependency>
    <groupId>science.aist.seshat</groupId>
    <artifactId>api</artifactId>
    <version>${seshat.version}</version>
    <scope>compile</scope> <!-- Note: this is default -->
</dependency>
```

Then you are able to use the Logger in the following way:

```java
Logger logger = Logger.getInstance(MyClass.class);

// Use the logger
logger.debug("I am a debugging message");
```

The get instance methods then scans the call path and tries to locate a LoggerFactory that implements the AbstractLoggerFactory
class. This factory is used to create the logger instances. You can add different loggers by adding them as a 
dependency.

e.g.
```xml
<dependency>
    <groupId>science.aist.seshat</groupId>
    <artifactId>log4j2</artifactId>
    <version>${seshat.version}</version>
    <scope>runtime</scope>
</dependency>
``` 

Please make sure, if your project is a library that should be used in other projects, to not force any user on a specific
logger implementation. Thus, the specific logger should only be imported if it is a standalone application. Otherwise,
include the logger implementations with test-scope. 

### How to set the log level for the default logger
Currently, there are two options to set the log level. The first one is by creating the logger (which then has an initial)
log level of `Logger.LOG_LEVEL`, which can then be set by calling the `.setLogLevel()`-Method.
If you want to initialize all your loggers with another default log level, you can set a system property via the maven pom file:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>${surefire.plugin.version}</version>
      <configuration>
        <systemPropertyVariables>
          <seshat.loglevel>{YOUR LOG LEVEL GOES HERE.}</seshat.loglevel>
        </systemPropertyVariables>
      </configuration>
     </plugin>
  </plugins>
</build>
```

This does not work when using java-main methods, because the maven system properties are not read then. If you want to set 
the system property for main method, you can configure that in your IntelliJ run configuration. For this, just 
add `-Dseshat.loglevel=YOUR_LOG_LEVEL` to the VM options. The VM option can of course also be set the same way, when 
executing it directly with java: e.g. `java -jar -Dseshat.loglevel=YOUR_LOG_LEVEL myJar.jar`. Maven supports it the same
way. e.g. `mvn clean package -Dseshat.loglevel=YOUR_LOG_LEVEL`.

### Building Seshat yourself

If you want to build the project yourself, just checkout this git repo, make sure you have jdk 11 or above installed as
well as maven 3.6.0 or above and build the project by running the maven command: `mvn package`. This results in a 
jar-file inside the target folder, which can be used as a dependency in other projects.

## FAQ

If you have any questions, please checkout our [FAQ](https://fhooeaist.github.io/seshat/faq.html) section.

## Contributing

**First make sure to read our [general contribution guidelines](https://fhooeaist.github.io/CONTRIBUTING.html).**

In addition to that, the following applies to this repository:

- Due to the focus on performance dependencies (except as bridges to other loggers) are not allowed. IF you have a very 
  good reason to add a dependency please state so in the corresponding issue / pull request.
   
## Licence

Copyright (c) 2020 the original author or authors.
DO NOT ALTER OR REMOVE COPYRIGHT NOTICES.

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.


[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FFHOOEAIST%2Fseshat.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FFHOOEAIST%2Fseshat?ref=badge_large)

## Research

If you are going to use this project as part of a research paper, we would ask you to reference this project by citing it. 

[![DOI](https://zenodo.org/badge/296271907.svg)](https://zenodo.org/badge/latestdoi/296271907)
