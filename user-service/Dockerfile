FROM ubuntu:latest
LABEL authors="OjiamboFelix"

ENTRYPOINT ["top", "-b"]

FROM openjdk:23-jdk-alpine

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "user-service.jar"]
