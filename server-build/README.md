# Usage of Fizio App Server
Server is used to expose endpoints and provide clinical data to authorized request that have a valid jwt token in the appropriate header.
Due to upload limit pull the project and then put the jar file downloaded from <https://drive.google.com/drive/folders/1nXiu0LbCXcpN_RW75srXRWiGXkb1_Bae?usp=share_link> in this folder.
# Installation

## 1. Configuration
In the folder there are three configuration files

- fizio-app.conf (no additional configuration)
- application.yaml
- log4j2.xml (no addtitional configuration)

### Application.yaml - Needed configuration
##### Properties to change based on keycloak
- issuer-uri: http://{HOST}:{PORT}/realms/{REALM_NAME}
> example: issuer-uri: http://localhost:8080/realms/fizio-test

- access-token-url: http://{HOST}:{PORT}/realms/{REALM_NAME}/protocol/openid-connect/token
> access-token-url: http://localhost:8080/realms/fizio-test/protocol/openid-connect/token

- authorization-url: http://{HOST}:{PORT}/realms/{REALM_NAME}/protocol/openid-connect/auth
> authorization-url: http://localhost:8080/realms/fizio-test/protocol/openid-connect/auth

- roles-path: resource_access/{REALM_NAME}/roles
> example: roles-path: resource_access/fizio-test/roles

##### Database properties to change
- url: jdbc:mysql://{HOST}:{PORT}/{DATABASE_NAME}
> example: url: jdbc:mysql://localhost:3306/fizio
- username: {USERNAME}
> example: username: root
- password: {PASSWORD}
> example: password: root

##### Server properties(Optional)
- port: {PORT}
> example: port: 8081

# 2. Requirments
- Navigate to base folder of server where fizio-app.jar is located and set Java version to 17.
- Keycloak started
- Database Server running with proper schema

# Usage
Navigate to the root directory.
Use the following three command in terminal:

Start the server
```sh
./fizio-app.jar start
```

Stop the server
```sh
./fizio-app.jar stop
```

Restart the server
```sh
./fizio-app.jar stop
```

