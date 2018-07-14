package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Pit;
import com.hkarabakla.kalahbackend.model.Player;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;

@NoArgsConstructor
public class PlayerCommand extends Command {

    @Override
    public void execute(Game game, Player player, Integer pitNo) {

        // TODO move this to controller

        if (pitNo < 0 || pitNo > 14) {
            throw new IllegalArgumentException("PitNo is not valid");
        }

        if (!player.getPitIndexes().contains(pitNo)) {
            throw new IllegalArgumentException("PitNo doesn't belong to the selected player");
        }

        Pit pit = game.getBoard()
                .getPits()
                .stream()
                .filter(p -> p.getOrderOnTheBoard() == pitNo)
                .findFirst()
                .get();

        if (pit.getKalah()) {
            throw new IllegalArgumentException("Pit is kalah, select another pit to play");
        } else if (pit.getStones() == 0) {
            throw new IllegalArgumentException("Pit is empty");
        }

        int stonesInTheSelectedPit = pit.getStones();
        pit.removeAllStones();

        if (stonesInTheSelectedPit == 1) {
            game.getBoard().setLastPitNo(pitNo + 1);
            game.getBoard().getPits()
                    .stream()
                    .filter(p -> p.getOrderOnTheBoard() == pitNo + 1)
                    .findFirst()
                    .get()
                    .addStone(stonesInTheSelectedPit);
        } else {
            IntStream.range(pitNo, pitNo + stonesInTheSelectedPit).forEachOrdered(value -> {
                int orderNo;
                if (player.getKalahPitOrderNumber().equals(7)) {
                    orderNo = value < 14 ? value % 14 : value % 14 + 1;
                } else {
                    orderNo = value < 7 ? value % 14 : value % 14 + 1; // TODO duzelt
                }

                Pit tempPit = game.getBoard().getPits().stream().filter(pit1 -> pit1.getOrderOnTheBoard().equals(orderNo)).findFirst().get();

                if (!(tempPit.getKalah() && !player.getPitIndexes().contains(tempPit.getOrderOnTheBoard()))) {
                    // tempPit is not opponent's kalah
                    tempPit.addStone(1);
                    game.getBoard().setLastPitNo(tempPit.getOrderOnTheBoard());
                }

            });
        }

        getNext().execute(game, player, pitNo);
    }
}
