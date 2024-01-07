#FROM ubuntu:latest
#LABEL authors="Admin"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} paymentservice.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","/paymentservice.jar"]
