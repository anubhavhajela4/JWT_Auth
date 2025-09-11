# Stage 1: Build the app with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (caching advantage)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy only the built jar from stage 1
COPY --from=build /app/target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
