package com.hkarabakla.kalahbackend.controller;

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
        Game gameMock = GameMocks.createGameMock();
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
}
