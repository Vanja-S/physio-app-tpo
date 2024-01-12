FROM eclipse-temurin:21-jdk-alpine
MAINTAINER Tadej Delopst <tadej.delopst@gmail.com>

RUN addgroup -S tpo && adduser -S tpo -G tpo

RUN mkdir -p /usr/app/tpo/logs && mkdir -p /usr/app/tpo/conf && chown -R tpo:tpo /usr/app/tpo

ADD ./fizio-app.jar /usr/app/tpo/fizio-app.jar
ADD conf/log4j2.xml /usr/app/tpo
ADD conf/application.yml /usr/app/tpo

VOLUME /usr/app/tpo/logs

WORKDIR /usr/app/tpo

EXPOSE 8081

USER tpo

ENTRYPOINT java -jar fizio-app.jar