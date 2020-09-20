# spring-docker
Complete Spring Boot multi-module example integrated with gradle, docker, docker-compose, postgresql and swaggerUI.

Use it as a starting base for your projects :)

## Requirements
Docker is required if you want to make use of the `buildDockerImage` gradle task and `docker-compose.yml` file. 

## Build
To build the jars for each module:
```bash
./gradlew build 
```

To build the docker image:
```bash
./gradlew buildDockerImage
```
Check with `docker images` that the image was created and tagged as `spring-docker`:
```bash
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
spring-docker       latest              9442eae8fbcd        26 seconds ago      390MB
```

## Run
```bash
docker-compose up
```
It creates the `dbpostgresql` and `spring-docker` containers in the same network.
```bash
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                            NAMES
91a222fee55a        spring-docker       "java -Xdebug -Xrunj…"   6 minutes ago       Up 6 minutes        0.0.0.0:7080->7080/tcp, 0.0.0.0:8080->8080/tcp   spring-docker
39df3ad6e3ce        postgres            "docker-entrypoint.s…"   4 weeks ago         Up 6 minutes        0.0.0.0:5432->5432/tcp                           dbpostgresql
```

## Modules
The project is composed by 2 modules. The persistence module contains the model and persistence classes and the service module contains the controller, application and swagger configuration classes.
```bash
spring-docker
├── persistence
│   └── src
│       └── main
│           └── java
│               └── com.example.springdocker.persistence
│                   ├── PokemonDTO.java
│                   ├── PokemonModel.java
│                   └── PokemonRepository.java
└── service
    └── src
        └── main
            └── java
                └── com.example.springdocker.service
                    ├── Application.java
                    ├── PokemonController.java
                    └── SwaggerConfig.java

```

## SwaggerUI
Default swaggerUI: http://localhost:8080/spring-docker/swagger-ui.html

PokemonController example: http://localhost:8080/spring-docker/swagger-ui.html#/pokemon-controller

## Examples
### CREATE
```bash
curl --location --request POST 'http://localhost:8080/spring-docker/pokemon/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Mewtwo",
    "type": "Psychic"
}'
```
### GET
```bash
curl --location --request GET 'http://localhost:8080/spring-docker/pokemon/random'
```
```bash
curl --location --request GET 'http://localhost:8080/spring-docker/pokemon/1'
```
```bash
curl --location --request GET 'http://localhost:8080/spring-docker/pokemon/all'
```
### DELETE
```bash
curl --location --request DELETE 'http://localhost:8080/spring-docker/pokemon/1'
```

