FROM maven:3.6.1-jdk-8 AS build_dir
WORKDIR /tmp

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM tomcat:9.0.36-jdk8-openjdk
COPY --from=build_dir /tmp/target/Loghmeh.war $CATALINA_HOME/webapps/Loghmeh.war
EXPOSE 8080