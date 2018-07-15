package com.hkarabakla.kalahbackend.controller;

import com.hkarabakla.kalahbackend.constants.MockConstants;
import com.hkarabakla.kalahbackend.controller.resource.GameResource;
import com.hkarabakla.kalahbackend.mocks.GameMocks;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameControllerTest {

    @Mock
    private GameService gameServiceMock;

    @Mock
    private ConfigurableConversionService conversionServiceMock;

    private GameController underTest;

    @Before
    public void setUp() {
        underTest = new GameController(gameServiceMock, conversionServiceMock);
    }

    @Test
    public void createGame() {
        // given
        Game gameMock = GameMocks.createFullGameMock();
        GameResource gameResourceMock = GameMocks.createGameResourceMock(gameMock);
        when(gameServiceMock.create()).thenReturn(gameMock);
        when(conversionServiceMock.convert(gameMock, GameResource.class)).thenReturn(gameResourceMock);

        // when
        ResponseEntity<GameResource> gameResource = underTest.create();

        // then
        assertEquals(HttpStatus.CREATED, gameResource.getStatusCode());
        assertNotNull(gameResource.getBody().getIdentifier());
        verify(gameServiceMock).create();
        // TODO add more asserts
    }

    @Test
    public void getGameById() {
        // given
        Game gameMock = GameMocks.createFullGameMock();
        GameResource gameResourceMock = GameMocks.createGameResourceMock(gameMock);
        when(gameServiceMock.getGameById(MockConstants.ID_1)).thenReturn(gameMock);
        when(conversionServiceMock.convert(gameMock, GameResource.class)).thenReturn(gameResourceMock);

        // when
        ResponseEntity<GameResource> gameResource = underTest.get(MockConstants.ID_1);

        // then
        assertEquals(HttpStatus.OK, gameResource.getStatusCode());
        assertNotNull(gameResource.getBody().getIdentifier());
        verify(gameServiceMock).getGameById(MockConstants.ID_1);
        // TODO add more asserts
    }

    @Test
    public void joinTheGame() {
        // given
        Game gameMock = GameMocks.createInitialGameMock();
        GameResource gameResourceMock = GameMocks.createGameResourceMock(gameMock);
        when(gameServiceMock.joinTheGame(MockConstants.ID_1)).thenReturn(gameMock);
        when(conversionServiceMock.convert(gameMock, GameResource.class)).thenReturn(gameResourceMock);

        // when
        ResponseEntity<GameResource> gameResource = underTest.joinTheGame(MockConstants.ID_1);

        // then
        assertEquals(HttpStatus.OK, gameResource.getStatusCode());
        assertNotNull(gameResource.getBody().getIdentifier());
        verify(gameServiceMock).joinTheGame(MockConstants.ID_1);
        // TODO add more asserts
    }

    @Test
    public void move() {
        // given
        Game gameMock = GameMocks.createInitialGameMock();
        GameResource gameResourceMock = GameMocks.createGameResourceMock(gameMock);
        when(gameServiceMock.move(MockConstants.ID_1, MockConstants.ID_2, MockConstants.PIT_NO_8)).thenReturn(gameMock);
        when(conversionServiceMock.convert(gameMock, GameResource.class)).thenReturn(gameResourceMock);

        // when
        ResponseEntity<GameResource> gameResource = underTest.move(MockConstants.ID_1, MockConstants.ID_2, MockConstants.PIT_NO_8);

        // then
        assertEquals(HttpStatus.OK, gameResource.getStatusCode());
        assertNotNull(gameResource.getBody().getIdentifier());
        verify(gameServiceMock).move(MockConstants.ID_1, MockConstants.ID_2, MockConstants.PIT_NO_8);
        // TODO add more asserts
    }
}
