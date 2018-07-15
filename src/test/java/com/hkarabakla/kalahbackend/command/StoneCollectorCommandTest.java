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
public class StoneCollectorCommandTest {

    @Mock
    private Command commandMock;

    private StoneCollectorCommand underTest;

    @Before
    public void setUp() {
        this.underTest = new StoneCollectorCommand();
        this.underTest.setNext(commandMock);
    }

    @Test
    public void executeRule5() {

        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_1)).findFirst().get().setStones(2);
        game.getBoard().setLastPitNo(MockConstants.PIT_NO_1);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_9;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(Optional.of(0).get(), game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_1)).findFirst().get().getStones());
        assertEquals(Optional.of(2).get(), game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_14)).findFirst().get().getStones());
    }


    @Test
    public void executeRule6() {

        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().setLastPitNo(MockConstants.PIT_NO_9);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_9)).findFirst().get().setStones(1);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_9;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(Optional.of(0).get(), game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_5)).findFirst().get().getStones());
        assertEquals(Optional.of(6).get(), game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_14)).findFirst().get().getStones());
    }

    @Test
    public void executeRule7() {

        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().setLastPitNo(MockConstants.PIT_NO_1);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_8)).findFirst().get().setStones(0);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_9)).findFirst().get().setStones(0);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_10)).findFirst().get().setStones(0);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_11)).findFirst().get().setStones(0);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_12)).findFirst().get().setStones(0);
        game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_13)).findFirst().get().setStones(0);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_9;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(Optional.of(36).get(), game.getBoard().getPits().stream().filter(pit -> pit.getOrderOnTheBoard().equals(MockConstants.PIT_NO_14)).findFirst().get().getStones());
    }
}
