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

import static com.hkarabakla.kalahbackend.util.GameUtil.getPitByOrderNo;
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
        assertEquals(1, getPitByOrderNo(game, MockConstants.PIT_NO_8).getStones().intValue());
        assertEquals(7, getPitByOrderNo(game, MockConstants.PIT_NO_9).getStones().intValue());
        assertEquals(7, getPitByOrderNo(game, MockConstants.PIT_NO_10).getStones().intValue());
        assertEquals(7, getPitByOrderNo(game, MockConstants.PIT_NO_11).getStones().intValue());
        assertEquals(7, getPitByOrderNo(game, MockConstants.PIT_NO_12).getStones().intValue());
        assertEquals(7, getPitByOrderNo(game, MockConstants.PIT_NO_13).getStones().intValue());
        assertEquals(0, getPitByOrderNo(game, MockConstants.PIT_NO_14).getStones().intValue());
    }

    @Test
    public void executeOneStoneInSelectedPitCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        getPitByOrderNo(game, MockConstants.PIT_NO_8).setStones(1);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_8;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(0, getPitByOrderNo(game, MockConstants.PIT_NO_8).getStones().intValue());
        assertEquals(7, getPitByOrderNo(game, MockConstants.PIT_NO_9).getStones().intValue());
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
        getPitByOrderNo(game, MockConstants.PIT_NO_8).setStones(0);
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
