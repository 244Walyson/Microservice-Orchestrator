FROM gradle:jdk17 AS builder
WORKDIR /app
COPY . /app
RUN gradle build -x test

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
