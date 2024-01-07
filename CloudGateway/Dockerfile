#FROM ubuntu:latest
#LABEL authors="Admin"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloudgateway.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar","/cloudgateway.jar"]
