package com.hkarabakla.kalahbackend.service;

import com.hkarabakla.kalahbackend.mocks.GameMocks;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

    @Mock
    private GameRepository gameRepositoryMock;

    private GameService underTest;

    @Before
    public void setUp() {
        underTest = new GameService(gameRepositoryMock, pitService, holeRepository);
    }

    @Test
    public void createGame() {
        // given
        Game gameMock = GameMocks.createGameMock();
        when(gameRepositoryMock.save(any())).thenReturn(gameMock);

        // when
        Game game = underTest.create();

        // then
        verify(gameRepositoryMock).save(any());
    }
}
