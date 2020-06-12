#FROM maven:3.6.1-jdk-8 AS build_dir
#WORKDIR /tmp/
#COPY . /tmp
#
#RUN mvn clean package
#
#FROM tomcat:9.0.16-jre8-alpine
#COPY --from=build_dir /tmp/target/Loghmeh.war $CATALINA_HOME/webapps/Loghmeh.war
#EXPOSE 8080


FROM maven:3.6.1-jdk-8 AS build_dir
WORKDIR /app
ADD pom.xml /app
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

ADD . /app
RUN ["mvn", "clean", "install", "-T", "2C", "-DskipTests=true"]
EXPOSE 8080
CMD mvn tomcat7:run