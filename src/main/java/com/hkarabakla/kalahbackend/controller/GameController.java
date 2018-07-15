package com.hkarabakla.kalahbackend.controller;

import com.hkarabakla.kalahbackend.controller.resource.GameResource;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.service.GameService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/games", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GameController {

    private final GameService gameService;
    private final ConfigurableConversionService conversionService;

    public GameController(GameService gameService, ConfigurableConversionService conversionService) {
        this.gameService = gameService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<GameResource> create() {
        Game game = gameService.create();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversionService.convert(game, GameResource.class));
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameResource> get(@PathVariable Long gameId) {
        Game game = gameService.getGameById(gameId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(conversionService.convert(game, GameResource.class));
    }

    @PutMapping("/{gameId}/join")
    public ResponseEntity<GameResource> joinTheGame(@PathVariable Long gameId) {
        Game game = gameService.joinTheGame(gameId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(conversionService.convert(game, GameResource.class));
    }

    @PutMapping("/{gameId}/players/{playerId}/pits/{pitNo}")
    public ResponseEntity<GameResource> move(@PathVariable Long gameId,
                                             @PathVariable Long playerId,
                                             @PathVariable Integer pitNo) {

        Game game = gameService.move(gameId, playerId, pitNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(conversionService.convert(game, GameResource.class));
    }
}
