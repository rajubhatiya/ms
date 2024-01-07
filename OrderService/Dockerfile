#FROM ubuntu:latest
#LABEL authors="Admin"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} orderservice.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","/orderservice.jar"]
