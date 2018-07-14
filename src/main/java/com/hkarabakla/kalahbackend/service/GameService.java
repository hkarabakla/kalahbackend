package com.hkarabakla.kalahbackend.service;

import com.hkarabakla.kalahbackend.command.CommandRunner;
import com.hkarabakla.kalahbackend.exception.ResourceNotFoundException;
import com.hkarabakla.kalahbackend.model.Attacker;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import com.hkarabakla.kalahbackend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PitService pitService;

    public GameService(GameRepository gameRepository, PitService pitService) {
        this.gameRepository = gameRepository;
        this.pitService = pitService;
    }

    public Game create() {

        Game game = Game.builder().build();
        return gameRepository.save(game);
    }

    public Game joinTheGame(Long gameId) {

        Game game = get(gameId);
        game.addPlayer();

        if(game.getPlayerOne() != null && game.getPlayerTwo() != null) {
            gameRepository.save(game);
            defineFirstAttacker(game);
        }

        return gameRepository.save(game);
    }

    private void defineFirstAttacker(Game game) {
        Random r = new Random();
        int randomPlayer = r.nextInt(2) + 1;
        if(randomPlayer == 1) {
            game.setAttacker(Attacker.builder()
                    .attackerId(game.getPlayerOne().getId())
                    .reason("First player").build());
        } else {
            game.setAttacker(Attacker.builder()
                    .attackerId(game.getPlayerTwo().getId())
                    .reason("Second player").build());
        }
    }

    public Game get(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id : " + gameId));
    }

    public Game move(Long gameId, Long playerId, Integer pitId) {

        Game game = get(gameId);
        Player player;

        if(game.getPlayerOne().getId().equals(playerId)) {
            player = game.getPlayerOne();
        } else if(game.getPlayerTwo().getId().equals(playerId)) {
            player = game.getPlayerTwo();
        } else {
            throw new ResourceNotFoundException("Player is not found with id : " + playerId);
        }

        CommandRunner commandRunner = new CommandRunner();
        commandRunner.run(game, player, pitId);

        return gameRepository.save(game);
    }
}
