# physio-app-tpo

A backend application for physio recoveries, it spans 4 differenent components:

- MySQL database for data persistence
- Fizio app which is a spring application
- Monitoring application made out in Flask python for monitoring DB, Keycloak, Spring server
- Keycloak for user management

## Docker compose

Docker compose takes all the locally built images and builds the stack. It consists of the following as mentioned

- MySQL database at db:3306
- Fizio app server at server:8081
- Monitoring application at monitoring:50000
- Keycloak at keycloak:8080

It uses the default docker compose network
