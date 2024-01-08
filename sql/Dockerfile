FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root

COPY ./physioapp_ddl.sql /docker-entrypoint-initdb.d/
