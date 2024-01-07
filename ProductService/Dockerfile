#FROM ubuntu:latest
#LABEL authors="Admin"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} productservice.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/productservice.jar"]
