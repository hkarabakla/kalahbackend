# Kalah Game Backend

Implementation of [Kalah](https://en.wikipedia.org/wiki/Kalah) Game with Spring Boot and Java 8

## Tech Stack
* Gradle : 4.8.1
* Spring Boot : 2.0.3
* Java 8
* JPA
* H2 

## Building the Application
In order to build the game just run the command below in the root folder of the application

    ./gradlew clean build

## Swagger UI
In order to access Swagger UI of services, just go to `http://localhost:8080/swagger-ui.html` from your browser.

## How to Play
1. Firstly create a game and keep game id to join the game
2. First player should join the game with game id, keep player id to continue and wait for the second player to start
3. Second player should join the game with game id, keep player id to continue
4. Game starts with the player's move who is chosen by the backend randomly

### Create Game
This endpoint creates new game.

`POST /api/v1/games`

### Get Game
This endpoint returns game by id.

`GET /api/v1/games/{gameId}`

### Join the Game
This endpoint joins the game if available.

`PUT /api/v1/games/{gameId}/join`  

### Move
This endpoint enable player to make a move.

`PUT /api/v1/games/{gameId}/players/{playerId}/pits/{pitNo}`  
