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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NextStatusIdentifierCommandTest {

    @Mock
    private Command commandMock;

    private NextAttackerIdentifierCommand underTest;

    @Before
    public void setUp() {
        this.underTest = new NextAttackerIdentifierCommand();
        this.underTest.setNext(commandMock);
    }

    @Test
    public void executeReplayCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_8;
        game.getBoard().setLastPitNo(player.getKalahPitOrderNumber());

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(player.getId(), game.getStatus().getAttackerId());
    }

    @Test
    public void executeTurnGoesFromPlayerOneToPlayerTwoCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerTwo();
        Integer pitNo = MockConstants.PIT_NO_8;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(game.getPlayerOne().getId(), game.getStatus().getAttackerId());
    }

    @Test
    public void executeTurnGoesFromPlayerTwoToPlayerOneCase() {
        // given
        Game game = GameMocks.createFullGameMock();
        Player player = game.getPlayerOne();
        Integer pitNo = MockConstants.PIT_NO_1;

        // when
        underTest.execute(game, player, pitNo);

        // then
        assertEquals(game.getPlayerTwo().getId(), game.getStatus().getAttackerId());
    }

}
