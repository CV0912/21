# Use an official OpenJDK runtime as a parent image
FROM maven:3.3.3-openjdk-22 AS build
COPY . .
RUN ./mvnw clean package
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/21.jar 21.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","21.jar" ]