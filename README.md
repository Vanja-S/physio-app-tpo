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
