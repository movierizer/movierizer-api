FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY movierizerapi-0.0.2.jar app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "./app.jar"]
