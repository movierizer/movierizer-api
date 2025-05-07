FROM eclipse-temurin:21-jre-alpine AS run

WORKDIR /app
COPY target/movierizerapi-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["java", "-jar", "app.jar"]