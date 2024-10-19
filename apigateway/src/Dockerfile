
FROM openjdk:23-jdk

# Set the working directory
WORKDIR /app

# Add the application's jar to the container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
