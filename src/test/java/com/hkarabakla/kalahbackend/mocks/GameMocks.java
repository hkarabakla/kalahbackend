package com.hkarabakla.kalahbackend.mocks;

import com.hkarabakla.kalahbackend.constants.MockConstants;
import com.hkarabakla.kalahbackend.controller.resource.GameResource;
import com.hkarabakla.kalahbackend.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hkarabakla.kalahbackend.util.GameUtil.getPitsForPlayer;

public class GameMocks {

    public static Game createInitialGameMock() {
        return Game.builder()
                .id(MockConstants.ID_1)
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
                .attacker(Attacker.builder()
                        .attackerId(MockConstants.ID_2)
                        .reason(MockConstants.REASON)
                        .build())
                .build();
    }

    public static Game getGameMockAfterFirstMoveForPlayerTwo() {
        Game fullGameMock = createFullGameMock();
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(8)).findFirst().get().setStones(1);
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(9)).findFirst().get().setStones(7);
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(10)).findFirst().get().setStones(7);
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(11)).findFirst().get().setStones(7);
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(12)).findFirst().get().setStones(7);
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(13)).findFirst().get().setStones(7);
        fullGameMock.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(14)).findFirst().get().setStones(1);
        fullGameMock.setAttacker(Attacker.builder().attackerId(MockConstants.ID_1).reason(MockConstants.REASON).build());
        fullGameMock.getBoard().setLastPitNo(14);
        return fullGameMock;
    }

    public static GameResource createGameResourceMock(Game game) {
        return GameResource.builder()
                .identifier(game.getId())
                .build();
    }


}
