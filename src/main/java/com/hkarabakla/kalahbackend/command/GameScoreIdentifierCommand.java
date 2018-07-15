package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.constants.GameStatus;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Pit;
import com.hkarabakla.kalahbackend.model.Player;
import com.hkarabakla.kalahbackend.model.Status;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

import static com.hkarabakla.kalahbackend.util.GameUtil.getPitByOrderNo;

@NoArgsConstructor
public class GameScoreIdentifierCommand extends Command {

    @Override
    void execute(Game game, Player player, Integer pitNo) {

        int playerOnesPitsSum = game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> game.getPlayerOne().getPitIndexes().contains(pit.getOrderOnTheBoard())
                        && !pit.getOrderOnTheBoard().equals(game.getPlayerOne().getKalahPitOrderNumber()))
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Pit::getStones)
                .sum();
        int playerTwosPitsSum = game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> game.getPlayerOne().getPitIndexes().contains(pit.getOrderOnTheBoard())
                        && !pit.getOrderOnTheBoard().equals(game.getPlayerOne().getKalahPitOrderNumber()))
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Pit::getStones)
                .sum();


        if (playerOnesPitsSum == 0 || playerTwosPitsSum == 0) {

            int playerOneKalahScore = getPitByOrderNo(game, game.getPlayerOne().getKalahPitOrderNumber()).getStones();
            int playerTwoKalahScore = getPitByOrderNo(game, game.getPlayerTwo().getKalahPitOrderNumber()).getStones();

            game.setStatus(Status.builder()
                    .status(GameStatus.END_OF_THE_GAME)
                    .firstPlayerScore(playerOnesPitsSum)
                    .secondPlayerScore(playerTwosPitsSum)
                    .winner(playerOneKalahScore > playerTwoKalahScore ? game.getPlayerOne().getId() : game.getPlayerTwo().getId())
                    .build());
        }
    }
}
