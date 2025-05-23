# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-ea-1-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target folder to the container
COPY target/21-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
