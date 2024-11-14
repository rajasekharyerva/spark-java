# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Copy the custom log4j2.xml file for logging configuration
COPY src/main/resources/log4j2.xml /app/log4j2.xml

# Build the project using Maven
RUN mvn clean package

# Copy the main application JAR and dependencies to the app directory
RUN cp target/*.jar app.jar && cp -r target/libs libs

# Set the entry point to run the Spark job with the correct classpath
ENTRYPOINT ["java", "-Dlog4j.configurationFile=file:/app/log4j2.xml", "-cp", "app.jar:libs/*", "org.example.SparkFileReader"]
