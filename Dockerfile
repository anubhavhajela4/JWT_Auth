# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file you built into the container
COPY target/JWT_Auth-0.0.1-SNAPSHOT.jar app.jar

# Expose port (default Spring Boot is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
