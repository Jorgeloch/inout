FROM eclipse-temurin:21-jre-alpine

ENV PROFILE="dev"

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xss512k", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app/app.jar"]
