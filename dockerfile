# Use a Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-21 as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project's pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image to run the application
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file from the builder stage
COPY --from=builder /app/target/course-work-0.0.1-SNAPSHOT.jar .

# Expose the port on which the app runs
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "course-work-0.0.1-SNAPSHOT.jar"]