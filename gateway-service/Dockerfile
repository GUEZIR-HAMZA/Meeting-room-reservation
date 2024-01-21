FROM ubuntu:latest
LABEL authors="guezi"

FROM openjdk:11
COPY . /app
WORKDIR /app
RUN ./mvnw clean install
CMD ["./mvnw", "spring-boot:run"]
