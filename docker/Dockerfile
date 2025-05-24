FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

ARG API_VERSION=0.1.3
COPY movierizerapi-${API_VERSION}.jar app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "./app.jar"]
