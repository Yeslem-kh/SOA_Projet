# Dockerfile pour Eureka Server
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copier le JAR
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port
EXPOSE 8761

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]