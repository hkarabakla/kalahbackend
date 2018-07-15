package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.constants.MockConstants;
import com.hkarabakla.kalahbackend.mocks.GameMocks;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerCommandTest {

    @Mock
    private Command commandMock;

    private PlayerCommand underTest;

    @Before
    public void setUp() {
        this.underTest = new PlayerCommand();
        this.underTest.setNext(commandMock);
    }

    @Test
    public void execute() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_8;

        // when
        underTest.execute(game, player, pitNo);

        // then
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
        assertEquals(Optional.of(0).get(), game.getBoard()
                .getPits()
                .stream()
                .filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_14))
                .findFirst()
                .get()
                .getStones());
    }

    @Test
    public void executeOneStoneInSelectedPitCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_8)).findFirst().get().setStones(1);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_8;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(Optional.of(0).get(), game.getBoard()
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
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeSelectedPitIsKalahCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_14;

        // when
        underTest.execute(game, player, pitNo);

        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeNoStonesInSelectedPitCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_8)).findFirst().get().setStones(0);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_8;

        // when
        underTest.execute(game, player, pitNo);

        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeInvalidPitNo() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_15;

        // when
        underTest.execute(game, player, pitNo);

        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeSelectedPitNoNotInPlayersPits() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_1;

        // when
        underTest.execute(game, player, pitNo);

        // then
    }
}
