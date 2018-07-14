package com.hkarabakla.kalahbackend.mocks;

import com.hkarabakla.kalahbackend.constants.MockConstants;
import com.hkarabakla.kalahbackend.controller.resource.GameResource;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;

import java.util.stream.Collectors;

import static com.hkarabakla.kalahbackend.util.GameUtil.getPitsForPlayer;

public class GameMocks {
    public static Game createGameMock() {
        return Game.builder()
                .id(MockConstants.ID_1)
                .playerOne(Player.builder()
                        .id(MockConstants.ID_1)
                        .pitIndexes(getPitsForPlayer(1).stream()
                                .map(pit -> pit.getOrderOnTheBoard())
                                .collect(Collectors.toList()))
                        .build())
                .playerTwo(Player.builder()
                        .id(MockConstants.ID_2)
                        .pitIndexes(getPitsForPlayer(2).stream()
                                .map(pit -> pit.getOrderOnTheBoard())
                                .collect(Collectors.toList()))
                        .build())
                .build();
    }

    public static GameResource createGameResourceMock(Game game) {
        return GameResource.builder()
                .identifier(game.getId())
                .build();
    }


}
