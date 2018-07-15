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
        getPitByOrderNo(game, MockConstants.PIT_NO_1).setStones(2);
        game.getBoard().setLastPitNo(MockConstants.PIT_NO_1);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_9;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(0, getPitByOrderNo(game, MockConstants.PIT_NO_1).getStones().intValue());
        assertEquals(2, getPitByOrderNo(game, MockConstants.PIT_NO_14).getStones().intValue());
    }


    @Test
    public void executeRule6() {

        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().setLastPitNo(MockConstants.PIT_NO_9);
        getPitByOrderNo(game, MockConstants.PIT_NO_9).setStones(1);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_9;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(0, getPitByOrderNo(game, MockConstants.PIT_NO_5).getStones().intValue());
        assertEquals(6, getPitByOrderNo(game, MockConstants.PIT_NO_14).getStones().intValue());
    }

    @Test
    public void executeRule7() {

        // given
        Game game = GameMocks.createFullGameMock();
        game.getBoard().setLastPitNo(MockConstants.PIT_NO_1);
        getPitByOrderNo(game, MockConstants.PIT_NO_8).setStones(0);
        getPitByOrderNo(game, MockConstants.PIT_NO_9).setStones(0);
        getPitByOrderNo(game, MockConstants.PIT_NO_10).setStones(0);
        getPitByOrderNo(game, MockConstants.PIT_NO_11).setStones(0);
        getPitByOrderNo(game, MockConstants.PIT_NO_12).setStones(0);
        getPitByOrderNo(game, MockConstants.PIT_NO_13).setStones(0);
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_9;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(36, getPitByOrderNo(game, MockConstants.PIT_NO_14).getStones().intValue());
    }
}
