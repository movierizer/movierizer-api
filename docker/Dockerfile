FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY movierizerapi.jar app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "./app.jar"]
