# physio-app-tpo

A backend application for physio recoveries, it spans 4 differenent components:

- MariaDb database for data persistence
- Fizio app which is a spring application
- Monitoring application made out in Flask python for monitoring DB, Keycloak, Spring server
- Keycloak for user management that also uses MariaDb for persistence.

## Build Requirments

- Java 17
- Maven

## Installation

To prepare deployment, run build.sh:

```sh
./build.sh
```

### Running docker compose

Docker compose file is located in the deployment directory.
Docker compose takes all the locally built images and builds the stack. It consists of the following as mentioned:

- MySQL database at localhost:3306
- Fizio app server at localhost:8081
- Monitoring application at localhost:5100
- Keycloak at localhost:8080

Start in the background:

```sh
docker compose up -d
```

Stopping:

```sh
docker compose down
```

Stopping and removing volumes:

```sh
docker compose down -v
```

Analyse the logs:

```sh
docker compose logs [server, mariadb, keycloak, monitoring] -f
```

 ## Usage

 After the stack is running, open keycloak on localhost:8080 and login using the following credentials:
 - username: admin
 - password: admin

From the Keycloak admin console, in the realm selection dropdown menu select CREATE REALM. Import fizio-realm.json located in deployment/keycloak-import directory into Resource file section via browse option. Proceed to create the realm using the CREATE button.

From the realm selection dropdown menu fizio realm should be present.
Proceed to select the created realm and select the section Users.
In the Users section you can find users that represent the patients (username structure: [a-zA-Z][a-zA-Z]\d{4} ex: ar5211).
- All patients have the password: tpo

Application is ready to use at [Server Dashboard API](http://localhost:8081/swagger-ui/index.html#/).
On the DashBoard API first authorize yourself via the Authorize button:
- username: ${One of patients usernames in keycloak}
- password: tpo
- client_id: fizio

Proceed to use exposed endpoints.

# Report

We created the docker compose file. Based on application structure, the first component that needs to be running is the database(keycloak and server depend on in). In first iteration we had a docker file present that downloaded the latest mysql image and then copies the ddl script into the volume of the image. Later we decided it best to use a constant image for persistance. To achieve that, we removed the docker file and incldued the following in the docker-compose.yml:

```yaml
mariadb:
  image: mariadb:11.2
  environment:
    MYSQL_ROOT_PASSWORD: root
  volumes:
    - mariadb_data:/var/lib/mysql
    - ./sql-init:/docker-entrypoint-initdb.d
  ports:
    - "3306:3306"
  networks:
    - tpo-network
```

We don't use latest, rather a specific version. That is if a new version comes, it will not affect the project. If the new version is compliant with the new database version, we can then proceed to upgrade in the docker compose file. After that we set the root password. Next the volumes. We move sql files located in the sql-init folder to the docker-entrypoint-initdb.d. That way we execute the statements when the image will be ran. On that we use a volume for /var/lib/mysql where data will be stored to ensure persistance. At the end we specify the ports between the Docker container and the host machine and the custom networks that the container will be connected to.

Next component is Monitoring. We created a docker file for the component:

```dockerfile
FROM python:3.11.2

WORKDIR /app

COPY ./requirements.txt /app
RUN pip install -r requirements.txt

COPY . .
EXPOSE 5000

ENV FLASK_APP=app.py

CMD ["flask", "run", "--host", "0.0.0.0"]
```
Here we use FROM to specify the base image will be Python 3.11.2. This image will be used as the starting point for building the component. Next we use WORKDIR to set the working directory inside the container to '/app'. In the next step we install the required libraries that are used in the component. We proceed to copy content of the directory into the container '/app' directory. Then we use EXPOSE and specify the port 5000. This documents that the application inside the container will listen on port 5000 (informative and doesn't publish the port). At the end we set envoirment variable FLASK_APP. This tells Flash where the starting point is.

After we finished with the docker file we included the following in docker-compose:

```yaml
monitoring:
    build:
      context: ./monitoring-dashboard
      dockerfile: Dockerfile
    ports:
      - "5100:5000"
    networks:
      - tpo-network    
```

In this configuration, we defined a service named monitoring. Our approach involved instructing Docker to build the service's image using the Dockerfile located in the ./monitoring-dashboard directory. To enable access to the monitoring service, we set up port mapping, directing traffic from port 5100 on the host machine to port 5000 within the container. Additionally, we connected the monitoring service to a custom network named tpo-network as the previous component.

Next component was Keycloak. In first iteration we had a docker file present. The docker file moved realm.json where the realm, client, roles and users were present into te appropriate import directory. In the docker compose we specified that the server is ran in dev mode. That means that it uses a H2 database that resets for each usage. In the second iteration we improved it to the level that it is ran in production mode. It also uses a database and stores its data there, resulting in a persistant component. We achieved this by removing the docker file and changing docker compose file:

```yaml
keycloak:
    image: quay.io/keycloak/keycloak:23.0.3
    depends_on:
      - "mariadb"
    ports:
      - "8080:8080"
    environment:
      - "KC_DB=mariadb"
      - "KC_DB_URL=jdbc:mariadb://mariadb:3306/keycloak"
      - "KC_DB_USERNAME=keycloakUser"
      - "KC_DB_PASSWORD=keycloakUser"
      - "KC_HTTPS_ENABLED=false"
      - "KC_HOSTNAME_STRICT_HTTPS=false"
      - "KC_HOSTNAME_STRICT=false"
      - "KC_HTTP_ENABLED=true"
      - "KC_DB_URL_DATABASE=keycloak"
      - "KEYCLOAK_ADMIN=admin"
      - "KEYCLOAK_ADMIN_PASSWORD=admin"
    entrypoint: '/opt/keycloak/bin/kc.sh start -Dkeycloak.profile.feature.upload_scripts=enabled'
    networks:
      - tpo-network  
```

So, we configured a Keycloak service using the following Docker Compose settings. The service is based on the image quay.io/keycloak/keycloak:23.0.3 (same argument for using a specific version instead of latest as in the database component) and depends on a MariaDB service indicated by the depends_on directive. To make Keycloak accessible, we mapped port 8080 on the host machine to port 8080 within the Keycloak container. The environment variables are set to configure Keycloak to use MariaDB as its database, specifying connection details such as database URL, username, and password. Other environment variables control Keycloak's behavior, including HTTPS settings and administrative credentials. The entrypoint is configured to start Keycloak with additional features enabled, such as the ability to upload scripts. Finally, the Keycloak service is connected to the custom network tpo-network for communication with other services. This comprehensive configuration aims to set up a Keycloak service with the necessary dependencies and settings that results in a higher quality component and will be easier to use in the next assignment.

The last component was the server. To prepare the usage we prepared a directory in the project folder consisting of:
- docker filer
- conf directory including application.yaml and log4j2.xml

Application properties configuration:
```yaml
spring:
  application:
    name: FizioApp
  security:
    oauth2:
      jwks-url: ${JWKS_URL}
      roles-path: resource_access/fizio/roles
      username-claim: preferred_username
      name-claim: name
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
  jpa:
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

server:
  port: 8081
  max-http-header-size: 65536
  servlet:
    context-path: /
  tomcat:
    accept-count: 32

management:
  endpoint:
    health:
      show-details: always
  endpoints:
    web:
      base-path: /

springdoc:
  swagger-ui:
    disable-swagger-default-url: true
```

Next we prepared a script that prepares the needed structure from the project directory:
```sh
#!/bin/bash

FIZIO_APP_DIR="fizio-app"
DEPLOYMENT_DIR="deployment"
SERVER_DIR="$DEPLOYMENT_DIR/server"

echo "Running 'mvn clean install -DskipTests' in $FIZIO_APP_DIR directory..."
cd "$FIZIO_APP_DIR" || exit 1
mvn clean install -DskipTests || exit 1
cd ..

if [ ! -d "$SERVER_DIR" ]; then
  echo "Creating $SERVER_DIR directory..."
  mkdir -p "$SERVER_DIR" || exit 1
fi

echo "Moving docker directory to $SERVER_DIR..."
cp -r "$FIZIO_APP_DIR/docker/" "$SERVER_DIR" || exit 1


echo "Moving $FIZIO_APP_DIR/target/fizio-app.jar to $SERVER_DIR..."
cp "$FIZIO_APP_DIR/target/fizio-app.jar" "$SERVER_DIR" || exit 1

echo "Script completed successfully." 
```

So to sum up. We build the project. This creates a jar file in the project's target directory. We move the jar file(since it cannot be pushed to github), docker file and the needed configuration into a new 'server' directory next to all components. In the docker file, in first iteration we moved the needed files to a volume. We saw that the problem is that this will work only for the local setup and the current local state. In this iteration we have written the following:

```yaml
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
```

This Dockerfile begins by specifying the base image using the FROM command, utilizing Eclipse Temurin's Alpine-based image with Java 21. Next we use the RUN command to create a user group (tpo) and a corresponding user (tpo) for the application's execution. The subsequent RUN command establishes directories for logs and configuration, setting ownership to the tpo user and group. Then we used the ADD command to copy the executable JAR file (fizio-app.jar), Log4j2 configuration file (log4j2.xml), and application configuration file (application.yml) from the host machine to their locations within the container. We declared a Docker volume at /usr/app/tpo/logs using the VOLUME command, indicating a designated location for persisting application logs. Then we used the WORKDIR command to set the working directory inside the container to /usr/app/tpo. Finally we set the ENTRYPOINT command specifies the default command to run when the container starts, launching the application using the Java command.

At the end we added the following to the docker-compose file:

```yaml
server:
    depends_on:
      - "mariadb"
      - "keycloak"
    build:
      context: ./server
      dockerfile: Dockerfile
    environment:
      - "JWKS_URL=http://keycloak:8080/realms/fizio/protocol/openid-connect/certs"
      - "spring.security.oauth2.authorization-url=http://localhost:8080/realms/fizio/protocol/openid-connect/auth" 
      - "spring.security.oauth2.access-token-url=http://localhost:8080/realms/fizio/protocol/openid-connect/token" 
      - "spring.security.oauth2.roles-path=resource_access/fizio/roles" 
      - "DATABASE_URL=jdbc:mysql://mariadb:3306/fizio"
      - "DATABASE_USERNAME=fizioUser"
      - "DATABASE_PASSWORD=fizioUser"
    ports:
      - "8081:8081"
    networks:
      - tpo-network  
    restart: always
```

In this Docker Compose configuration block, we specified the setup of a server service the server. The depends_on directive ensures that the server service is dependent on the successful startup of both the "mariadb" and "keycloak" services. Next the build section specifies the context as "./server" and the Dockerfile as "Dockerfile," indicating the location and name of the Dockerfile to use for building the server image. Environment variables are then defined, configuring the server's interaction with Keycloak and MariaDB, such as the JWKS URL, OAuth2 authorization and token URLs, roles path, and database connection details. Here we can see some properties are named with upper case and appear in the application.yaml. This will be injected into the file with the provided configuration. We wanted to exclude this from the application.yaml file since we figured this will be helpful and needed if by any chance any adresses are decided dynamically. The ports directive maps port 8081 on the host machine to port 8081 within the server container, allowing external access. The service is connected to the "tpo-network" for communication with other services. The restart: always ensures that the server service restarts automatically in case of failure or system reboot. We used this command since depends on ensures that the depended services start before it, but from testing we saw that the database sometimes did not set up in completion resulting in a failure because connection was not yet available. We also resolved this problem when we decided to not download the db image in every run.
