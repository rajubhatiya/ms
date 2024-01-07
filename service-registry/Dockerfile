#FROM ubuntu:latest
#LABEL authors="Admin"
#
#ENTRYPOINT ["top", "-b"]
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} serviceregistry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar","/serviceregistry.jar"]
