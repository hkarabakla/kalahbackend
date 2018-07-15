package com.hkarabakla.kalahbackend.util;

import com.hkarabakla.kalahbackend.constants.GameConstants;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Pit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GameUtil {

    public static List<Pit> getPitsForPlayer(int playerNo) {

        List<Pit> pits = new ArrayList<>();

        IntStream.range(1, 8).forEachOrdered(order -> {
            boolean isKalah = order * playerNo % 7 == 0;
            pits.add(Pit.builder()
                    .orderOnTheBoard(order + (playerNo - 1) * 7)
                    .kalah(isKalah)
                    .stones(isKalah ? 0 : GameConstants.STONE_COUNT)
                    .build());
        });

        return pits;
    }

    public static Pit getPitByOrderNo(Game game, Integer pitNo) {
        return game.getBoard()
                .getPits()
                .stream()
                .filter(p -> p.getOrderOnTheBoard().equals(pitNo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid pit no"));
    }
}
