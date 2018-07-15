package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Pit;
import com.hkarabakla.kalahbackend.model.Player;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hkarabakla.kalahbackend.util.GameUtil.getPitByOrderNo;

@NoArgsConstructor
class StoneCollectorCommand extends Command {

    private static final Map<Integer, Integer> opponentMap;

    static {
        opponentMap = new HashMap<>();
        opponentMap.put(1, 13);
        opponentMap.put(2, 12);
        opponentMap.put(3, 11);
        opponentMap.put(4, 10);
        opponentMap.put(5, 9);
        opponentMap.put(6, 8);

        opponentMap.put(13, 1);
        opponentMap.put(12, 2);
        opponentMap.put(11, 3);
        opponentMap.put(10, 4);
        opponentMap.put(9, 5);
        opponentMap.put(8, 6);
    }

    @Override
    void execute(Game game, Player player, Integer pitNo) {

        Pit latestPit = getPitByOrderNo(game, game.getBoard().getLastPitNo());

        Pit playersKalah = getPitByOrderNo(game, player.getKalahPitOrderNumber());

        if (!player.getPitIndexes().contains(game.getBoard().getLastPitNo())) {
            if (latestPit.getStones() % 2 == 0) {
                int stones = latestPit.getStones();
                latestPit.removeAllStones();
                playersKalah.addStone(stones);
            }
        }

        if (player.getPitIndexes().contains(game.getBoard().getLastPitNo())) {
            if (latestPit.getStones().equals(1) && !player.getKalahPitOrderNumber().equals(latestPit.getOrderOnTheBoard())) {
                Optional<Pit> opponentPit = game
                        .getBoard()
                        .getPits()
                        .stream()
                        .filter(pit -> pit.getOrderOnTheBoard().equals(opponentMap.get(game.getBoard().getLastPitNo())))
                        .findFirst();
                if (opponentPit.isPresent()) {
                    int stones = opponentPit.get().getStones();
                    opponentPit.get().removeAllStones();
                    playersKalah.addStone(stones);
                }
            }
        }

        Integer playersPitsSum = game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> player.getPitIndexes().contains(pit.getOrderOnTheBoard())
                        && !pit.getOrderOnTheBoard().equals(player.getKalahPitOrderNumber()))
                .collect(Collectors.toList())
                .stream()
                .mapToInt(Pit::getStones)
                .sum();
        if (playersPitsSum.equals(0)) {
            game.getBoard()
                    .getPits()
                    .stream()
                    .filter(pit -> !player.getPitIndexes().contains(pit.getOrderOnTheBoard()))
                    .forEach(pit -> {
                        if (!pit.getKalah()) {
                            playersKalah.addStone(pit.getStones());
                            pit.removeAllStones();
                        }
                    });
        }


        getNext().execute(game, player, pitNo);
    }


}
