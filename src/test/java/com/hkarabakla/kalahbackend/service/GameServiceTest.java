package com.hkarabakla.kalahbackend.service;

import com.google.common.collect.ObjectArrays;
import com.hkarabakla.kalahbackend.constants.MockConstants;
import com.hkarabakla.kalahbackend.exception.ForbiddenOperationException;
import com.hkarabakla.kalahbackend.exception.ResourceNotFoundException;
import com.hkarabakla.kalahbackend.mocks.GameMocks;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Pit;
import com.hkarabakla.kalahbackend.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

    @Mock
    private GameRepository gameRepositoryMock;

    private GameService underTest;

    @Before
    public void setUp() {
        underTest = new GameService(gameRepositoryMock);
    }

    @Test
    public void createGame() {
        // given
        Game gameMock = GameMocks.createFullGameMock();
        when(gameRepositoryMock.save(any())).thenReturn(gameMock);

        // when
        underTest.create();

        // then
        verify(gameRepositoryMock).save(any());
    }

    @Test
    public void getGameById() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createFullGameMock()));

        // when
        Game gameById = underTest.getGameById(MockConstants.ID_1);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
        assertNotNull(gameById);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getGameByIdResourceNotFound() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.empty());

        // when
        Game gameById = underTest.getGameById(MockConstants.ID_1);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
        assertNull(gameById);
    }

    @Test
    public void joinTheGameForFirstPlayer() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createInitialGameMock()));
        when(gameRepositoryMock.save(any())).thenReturn(GameMocks.createGameWithOnePlayerMock());

        // when
        Game game = underTest.joinTheGame(MockConstants.ID_1);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
        assertEquals(MockConstants.ID_1, game.getId());
        assertNotNull(game.getPlayerOne());
        assertNotNull(game.getBoard());
        assertArrayEquals(game.getBoard().getPits().stream().map(Pit::getOrderOnTheBoard).toArray(),
                game.getPlayerOne().getPitIndexes().toArray(new Integer[7]));
    }

    @Test
    public void joinTheGameForSecondPlayer() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createGameWithOnePlayerMock()));
        when(gameRepositoryMock.save(any())).thenReturn(GameMocks.createFullGameMock());

        // when
        Game game = underTest.joinTheGame(MockConstants.ID_1);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
        assertEquals(MockConstants.ID_1, game.getId());
        assertNotNull(game.getPlayerOne());
        assertNotNull(game.getPlayerTwo());
        assertNotNull(game.getBoard());
        assertNotNull(game.getAttacker());
        assertArrayEquals(game.getBoard().getPits().stream().map(Pit::getOrderOnTheBoard).toArray(),
                ObjectArrays.concat(game.getPlayerOne().getPitIndexes().toArray(new Integer[7]),
                        game.getPlayerTwo().getPitIndexes().toArray(new Integer[7]),
                        Integer.class));
    }

    @Test(expected = ForbiddenOperationException.class)
    public void joinTheGameForbidden() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createFullGameMock()));

        // when
        underTest.joinTheGame(MockConstants.ID_1);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
    }

    @Test
    public void move() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createFullGameMock()));
        when(gameRepositoryMock.save(any())).thenReturn(GameMocks.getGameMockAfterFirstMoveForPlayerTwo());


        // when
        Game game = underTest.move(MockConstants.ID_1, MockConstants.ID_2, MockConstants.PIT_NO_8);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
        assertNotNull(game);
        assertEquals(MockConstants.ID_1, game.getId());
        assertNotNull(game.getPlayerOne());
        assertNotNull(game.getPlayerTwo());
        assertNotNull(game.getBoard());
        assertNotNull(game.getAttacker());
        assertEquals(Optional.of(1).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_8))
                .findFirst()
                .get()
                .getStones());
        assertEquals(Optional.of(7).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_9))
                .findFirst()
                .get()
                .getStones());
        assertEquals(Optional.of(7).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_10))
                .findFirst()
                .get()
                .getStones());
        assertEquals(Optional.of(7).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_11))
                .findFirst()
                .get()
                .getStones());
        assertEquals(Optional.of(7).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_12))
                .findFirst()
                .get()
                .getStones());
        assertEquals(Optional.of(7).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_13))
                .findFirst()
                .get()
                .getStones());
        assertEquals(Optional.of(1).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_14))
                .findFirst()
                .get()
                .getStones());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void moveInvalidPlayerId() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createFullGameMock()));

        // when
        underTest.move(MockConstants.ID_1, MockConstants.ID_3, MockConstants.PIT_NO_8);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void moveUnAuthorizedPlayerId() {
        // given
        when(gameRepositoryMock.findById(MockConstants.ID_1)).thenReturn(Optional.ofNullable(GameMocks.createFullGameMock()));

        // when
        underTest.move(MockConstants.ID_1, MockConstants.ID_1, MockConstants.PIT_NO_8);

        // then
        verify(gameRepositoryMock).findById(MockConstants.ID_1);
    }
}
