FROM eclipse-temurin:21

WORKDIR /app

COPY . .
RUN chmod +x mvnw
EXPOSE 5000

CMD ["./mvnw", "spring-boot:run"]