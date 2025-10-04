# Стадия сборки
FROM gradle:7.6.1-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

# Стадия запуска
FROM openjdk:17-alpine
VOLUME /tmp
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
