FROM eclipse-temurin:21 AS build 

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine AS run

WORKDIR /app
COPY --from=build /app/target/movierizerapi-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5000

CMD ["java", "-jar", "app.jar"]