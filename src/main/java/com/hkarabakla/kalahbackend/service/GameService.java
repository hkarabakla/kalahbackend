package com.hkarabakla.kalahbackend.service;

import com.hkarabakla.kalahbackend.command.CommandRunner;
import com.hkarabakla.kalahbackend.constants.GameStatus;
import com.hkarabakla.kalahbackend.exception.ResourceNotFoundException;
import com.hkarabakla.kalahbackend.model.Status;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import com.hkarabakla.kalahbackend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create() {

        Game game = Game.builder()
                .status(Status.builder()
                        .status(GameStatus.WAITING_FOR_FIRST_PLAYER)
                        .build())
                .build();
        return gameRepository.save(game);
    }

    public Game joinTheGame(Long gameId) {

        Game game = getGameById(gameId);
        game.addPlayer();

        if(game.getPlayerOne() != null && game.getPlayerTwo() != null) {
            gameRepository.save(game);
            defineFirstAttacker(game);
        }

        return gameRepository.save(game);
    }

    private void defineFirstAttacker(Game game) {
        Random r = new Random();
        int randomPlayerNumber = r.nextInt(2) + 1;
        if(randomPlayerNumber == 1) {
            game.setStatus(Status.builder()
                    .attackerId(game.getPlayerOne().getId())
                    .status(GameStatus.WAITING_FOR_FIRST_PLAYERS_ATTACK).build());
        } else {
            game.setStatus(Status.builder()
                    .attackerId(game.getPlayerTwo().getId())
                    .status(GameStatus.WAITING_FOR_SECOND_PLAYERS_ATTACK).build());
        }
    }

    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id : " + gameId));
    }

    public Game move(Long gameId, Long playerId, Integer pitNo) {

        Game game = getGameById(gameId);
        Player player;

        if(game.getPlayerOne().getId().equals(playerId)) {
            player = game.getPlayerOne();
        } else if(game.getPlayerTwo().getId().equals(playerId)) {
            player = game.getPlayerTwo();
        } else {
            throw new ResourceNotFoundException("Player is not found with id : " + playerId);
        }

        if (!game.getStatus().getAttackerId().equals(playerId)) {
            throw new IllegalArgumentException("It is " + game.getStatus().getAttackerId() + " 's turn !");
        }

        CommandRunner commandRunner = new CommandRunner();
        commandRunner.run(game, player, pitNo);

        return gameRepository.save(game);
    }
}
