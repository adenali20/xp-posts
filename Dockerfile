#FROM openjdk:17-jdk-alpine
FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} xp-expenses.jar
EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/xp-expenses.jar"]