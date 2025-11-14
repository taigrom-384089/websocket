# Stage 1: Build the application
# Use a standard Java build image with Maven pre-installed
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the entire project structure for the build
# This includes pom.xml and the src directory
COPY . /app

# The mvn package command handles dependency resolution, compilation, and creating the final JAR
# -DskipTests is crucial for a faster build
RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller runtime image (JRE only)
# Use a smaller JRE image for the final container
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# Copy the built JAR from the 'build' stage
# Adjust the JAR name pattern if your artifactId is different
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (default for Spring Boot)
EXPOSE 8080:8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]