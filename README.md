# Employee Search Coding Assessment

This is a competency assessment completed by Jarom Schertz.

# Dependencies
 - NPM
 - Java 8+ (Tested with java 8 and 11)
 - Apache Maven

If using a version of java other than 8, make sure to update the EXLEmployeeSearch/pom.xml file with the correct Java version.

# Database Configuration
By default, the application is configured to use an in memory, H2 database, but you can configure to use any database supported by spring. Information on how to configure can be found here: [Spring.io](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/boot-features-sql.html).

# Manual Build/Run
 - Verify that npm, java, and maven are all on your path
 - Open EXLEmployeeSearchFE directory and run the command "npm install"
 - If you have @angular/cli installed in your npm global, run the command "ng build --prod" otherwise run the command "npx -p @angular/cli ng build --prod"
 - Change directories to EXLEmployeeSearch/EXLEmployeeSearch. For reference you should see a pom.xml in this diretory.
 - Run the command "mvn spring-boot:run"
 - The application should now be running on http://localhost:8080

# Automated Build/Run

## MS Windows:
 - Edit run.bat and update the paths for JAVA_HOME and MAVEN_HOME for your setup
 - Execute run.bat
 - Wait for script to complete build
 - The application should now be running on http://localhost:8080

## Linux / MacOS:
 - Edit run.sh and update teh pats for JAVA_HOME and MAVEN_HOME for your setup
 - Execute run.sh
 - Wait for script to complete build
 - The application should now be running on http://localhost:8080