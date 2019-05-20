package com.buttons.mastermind.application.service;

import com.buttons.mastermind.application.dto.CreateGameDto;
import com.buttons.mastermind.domain.exception.InvalidColorsException;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.model.GameRepository;
import com.buttons.mastermind.domain.game.service.IdGeneratorService;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CreateGameServiceTest {

    @Autowired
    private CreateGameService createGameService;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private IdGeneratorService idGeneratorService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        GameId gameId = mock(GameId.class);
        when(gameId.getId()).thenReturn("AAAA-BBBB-CCCC-DDDD");

        when(idGeneratorService.generate()).thenReturn(gameId);
    }

    @Test
    public void testCreateCorrectGame()
    {
        CreateGameRequest request = mock(CreateGameRequest.class);
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("G");

        CreateGameDto response = createGameService.execute(request);

        Assert.assertEquals("AAAA-BBBB-CCCC-DDDD", response.getId());
        Assert.assertEquals(Game.DEFAULT_ATTEMPTS, response.getAttempts());

    }

    @Test
    public void testExceptionInvalidColor()
    {
        CreateGameRequest request = mock(CreateGameRequest.class);
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("X");

        exceptionRule.expect(InvalidColorsException.class);
        CreateGameDto response = createGameService.execute(request);
    }
}
