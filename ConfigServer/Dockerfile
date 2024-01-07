#FROM ubuntu:latest
#LABEL authors="Admin"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} configserver.jar
EXPOSE 9296
ENTRYPOINT ["java", "-jar","/configserver.jar"]
