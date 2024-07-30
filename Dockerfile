# Use the official Maven image to build the application
FROM maven:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use the official OpenJDK 21 image to run the application
FROM openjdk:24-slim-bullseye

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
