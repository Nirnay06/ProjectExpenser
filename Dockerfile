# Use a base image with Java 11 installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR into the container
COPY target/project-expenser-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application runs on (if applicable)
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
