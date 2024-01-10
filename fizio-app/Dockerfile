FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp

COPY ./target/fizio-app-1.0.0-SNAPSHOT/* /fizio-app/

ENTRYPOINT ["java","-jar","/fizio-app/fizio-app.jar"]