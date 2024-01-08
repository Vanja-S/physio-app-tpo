FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp

COPY ./target/*.jar fizio-app.jar

ENTRYPOINT ["java","-jar","/fizio-app.jar"]