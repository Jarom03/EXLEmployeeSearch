set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_161
set MAVEN_HOME=C:\apache-maven-3.6.3

set PATH=%PATH%;%JAVA_HOME%/bin;%MAVEN_HOME%/bin

cd ./EXLEmployeeSearchFE
call npm install
call npx -p @angular/cli ng build --prod
cd ../EXLEmployeeSearch
call mvn clean package
call mvn spring-boot:run
