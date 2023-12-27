#!/bin/bash

mvn spring-boot:run -Dskip.node -DskipCodeQualityCheck=true -Dmaven.test.skip=true -DskipCompression=true -Dskip.npm -Dspring.devtools.restart.enabled=true -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
