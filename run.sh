#!/bin/zsh

set JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.6.jdk/Contents/Home
set MAVEN_HOME=/opt/apache-maven-3.6.3

export PATH=$PATH:JAVA_HOME/bin:MAVEN_HOME/bin

cd ./EXLEmployeeSearchFE
ng build --prod
cd ../EXLEmployeeSearch
mvn clean package
mvn spring-boot:run
