# Use official JDK image
FROM openjdk:19-jdk-slim

# Set app directory
WORKDIR /app

# Copy Maven build JAR file (make sure you build the jar first with mvn clean package -DskipTests)
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
