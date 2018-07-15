package com.hkarabakla.kalahbackend.mocks;

import com.hkarabakla.kalahbackend.constants.GameStatus;
import com.hkarabakla.kalahbackend.constants.MockConstants;
import com.hkarabakla.kalahbackend.controller.resource.GameResource;
import com.hkarabakla.kalahbackend.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hkarabakla.kalahbackend.util.GameUtil.getPitByOrderNo;
import static com.hkarabakla.kalahbackend.util.GameUtil.getPitsForPlayer;

public class GameMocks {

    public static Game createInitialGameMock() {
        return Game.builder()
                .id(MockConstants.ID_1)
                .status(Status.builder()
                        .status(GameStatus.WAITING_FOR_FIRST_PLAYER)
                        .build())
                .build();
    }

    public static Game createGameWithOnePlayerMock() {
        List<Pit> pitsForPlayer = getPitsForPlayer(1);
        return Game.builder()
                .id(MockConstants.ID_1)
                .playerOne(Player.builder()
                        .id(MockConstants.ID_1)
                        .pitIndexes(pitsForPlayer.stream()
                                .map(Pit::getOrderOnTheBoard)
                                .collect(Collectors.toList()))
                        .build())
                .board(Board.builder()
                        .pits(pitsForPlayer)
                        .build())
                .build();
    }

    public static Game createFullGameMock() {
        List<Pit> pitsForPlayer1 = getPitsForPlayer(1);
        List<Pit> pitsForPlayer2 = getPitsForPlayer(2);
        List<Pit> allPits = new ArrayList<>();
        allPits.addAll(pitsForPlayer1);
        allPits.addAll(pitsForPlayer2);
        return Game.builder()
                .id(MockConstants.ID_1)
                .playerOne(Player.builder()
                        .id(MockConstants.ID_1)
                        .pitIndexes(pitsForPlayer1.stream()
                                .map(Pit::getOrderOnTheBoard)
                                .collect(Collectors.toList()))
                        .kalahPitOrderNumber(7)
                        .build())
                .playerTwo(Player.builder()
                        .id(MockConstants.ID_2)
                        .pitIndexes(pitsForPlayer2.stream()
                                .map(Pit::getOrderOnTheBoard)
                                .collect(Collectors.toList()))
                        .kalahPitOrderNumber(14)
                        .build())
                .board(Board.builder()
                        .pits(allPits)
                        .build())
                .status(Status.builder()
                        .attackerId(MockConstants.ID_2)
                        .status(MockConstants.STATUS)
                        .build())
                .build();
    }

    public static Game getGameMockAfterFirstMoveForPlayerTwo() {
        Game fullGameMock = createFullGameMock();
        getPitByOrderNo(fullGameMock, 8).setStones(1);
        getPitByOrderNo(fullGameMock, 9).setStones(7);
        getPitByOrderNo(fullGameMock, 10).setStones(7);
        getPitByOrderNo(fullGameMock, 11).setStones(7);
        getPitByOrderNo(fullGameMock, 12).setStones(7);
        getPitByOrderNo(fullGameMock, 13).setStones(7);
        getPitByOrderNo(fullGameMock, 14).setStones(1);
        fullGameMock.setStatus(Status.builder().attackerId(MockConstants.ID_1).status(MockConstants.STATUS).build());
        fullGameMock.getBoard().setLastPitNo(14);
        return fullGameMock;
    }

    public static GameResource createGameResourceMock(Game game) {
        return GameResource.builder()
                .identifier(game.getId())
                .build();
    }
}
