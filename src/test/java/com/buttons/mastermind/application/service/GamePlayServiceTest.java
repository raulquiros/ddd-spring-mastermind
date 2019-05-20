package com.buttons.mastermind.application.service;

import com.buttons.mastermind.application.dto.GamePlayDto;
import com.buttons.mastermind.domain.exception.InvalidColorsException;
import com.buttons.mastermind.domain.exception.InvalidGameIdException;
import com.buttons.mastermind.domain.exception.OvercomeAttemptsException;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.model.GameRepository;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import com.buttons.mastermind.domain.game.valueObject.GamePlay;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GamePlayServiceTest {

    private static final String GAME_ID = "AAAA-BBBB-CCCC-DDDD";

    @Autowired
    private GamePlayService gamePlayService;

    @MockBean
    private GameRepository gameRepository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void testCreateCorrectPlayWithNulls()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("B");
        when(request.getColor2()).thenReturn("B");
        when(request.getColor3()).thenReturn("B");
        when(request.getColor4()).thenReturn("B");

        GameId gameId = new GameId(GAME_ID);
        Game game = mock(Game.class);
        when(game.getColors()).thenReturn(new String[]{"R","G","R","G"});
        when(game.getAttempts()).thenReturn(15);

        ArrayList al = new ArrayList<GamePlay>();
        String[] colors = new String[]{"B", "B", "B", "B"};
        String[] results = new String[]{null, null, null, null};
        al.add(new GamePlay(colors, results));
        when(game.getGamePlays()).thenReturn(al);

        when(gameRepository.getGameById(refEq(gameId, gameId.getId()))).thenReturn(game);

        GamePlayDto response = gamePlayService.execute(request);

        Assert.assertNull(response.getColor1());
        Assert.assertNull(response.getColor2());
        Assert.assertNull(response.getColor3());
        Assert.assertNull(response.getColor4());
    }

    @Test
    public void testCreateCorrectPlayWithWhites()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("G");
        when(request.getColor2()).thenReturn("R");
        when(request.getColor3()).thenReturn("G");
        when(request.getColor4()).thenReturn("R");

        GameId gameId = new GameId(GAME_ID);
        Game game = mock(Game.class);
        when(game.getColors()).thenReturn(new String[]{"R","G","R","G"});
        when(game.getAttempts()).thenReturn(15);

        ArrayList al = new ArrayList<GamePlay>();
        String[] colors = new String[]{"G", "R", "G", "R"};
        String[] results = new String[]{"W", "W", "W", "W"};
        al.add(new GamePlay(colors, results));
        when(game.getGamePlays()).thenReturn(al);

        when(gameRepository.getGameById(refEq(gameId, gameId.getId()))).thenReturn(game);

        GamePlayDto response = gamePlayService.execute(request);

        Assert.assertEquals(response.getColor1(), "W");
        Assert.assertEquals(response.getColor2(), "W");
        Assert.assertEquals(response.getColor3(), "W");
        Assert.assertEquals(response.getColor4(), "W");
    }

    @Test
    public void testCreateCorrectPlayWinner()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("G");

        GameId gameId = new GameId(GAME_ID);
        Game game = mock(Game.class);
        when(game.getColors()).thenReturn(new String[]{"R","G","R","G"});
        when(game.getAttempts()).thenReturn(15);

        ArrayList al = new ArrayList<GamePlay>();
        String[] colors = new String[]{"R", "G", "R", "G"};
        String[] results = new String[]{"B", "B", "B", "B"};
        al.add(new GamePlay(colors, results));
        when(game.getGamePlays()).thenReturn(al);

        when(gameRepository.getGameById(refEq(gameId, gameId.getId()))).thenReturn(game);

        GamePlayDto response = gamePlayService.execute(request);

        Assert.assertEquals(response.getColor1(), "B");
        Assert.assertEquals(response.getColor2(), "B");
        Assert.assertEquals(response.getColor3(), "B");
        Assert.assertEquals(response.getColor4(), "B");
    }

    @Test
    public void testCreateCorrectPlayWithAllOptions()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("B");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("R");

        GameId gameId = new GameId(GAME_ID);
        Game game = mock(Game.class);
        when(game.getColors()).thenReturn(new String[]{"R","G","R","G"});
        when(game.getAttempts()).thenReturn(15);

        ArrayList al = new ArrayList<GamePlay>();
        String[] colors = new String[]{"Y", "G", "R", "R"};
        String[] results = new String[]{null, "B", "B", "W"};
        al.add(new GamePlay(colors, results));
        when(game.getGamePlays()).thenReturn(al);

        when(gameRepository.getGameById(refEq(gameId, gameId.getId()))).thenReturn(game);

        GamePlayDto response = gamePlayService.execute(request);

        Assert.assertNull(response.getColor1());
        Assert.assertEquals(response.getColor2(), "B");
        Assert.assertEquals(response.getColor3(), "B");
        Assert.assertEquals(response.getColor4(), "W");
    }

    @Test
    public void testExceptionInvalidColor()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("X");

        exceptionRule.expect(InvalidColorsException.class);
        GamePlayDto dto = gamePlayService.execute(request);
    }

    @Test
    public void testExceptionWithNullColor()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn(null);

        exceptionRule.expect(InvalidColorsException.class);
        GamePlayDto dto = gamePlayService.execute(request);
    }

    @Test
    public void testExceptionInvalidGameId()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn("1234-1234-1234-1234");
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("G");

        GameId gameId = new GameId("1234-1234-1234-1234");

        when(gameRepository.getGameById(refEq(gameId, gameId.getId()))).thenThrow(InvalidGameIdException.class);

        exceptionRule.expect(InvalidGameIdException.class);
        GamePlayDto dto = gamePlayService.execute(request);
    }

    @Test
    public void testExceptionOvercomeAttempts()
    {
        GamePlayRequest request = mock(GamePlayRequest.class);
        when(request.getId()).thenReturn(GAME_ID);
        when(request.getColor1()).thenReturn("R");
        when(request.getColor2()).thenReturn("G");
        when(request.getColor3()).thenReturn("R");
        when(request.getColor4()).thenReturn("G");


        GameId gameId = new GameId(GAME_ID);
        Game game = mock(Game.class);
        when(game.getColors()).thenReturn(new String[]{"R","G","R","G"});
        when(game.getAttempts()).thenReturn(0);

        ArrayList al = new ArrayList<GamePlay>();
        String[] colors = new String[]{"Y", "G", "R", "R"};
        String[] results = new String[]{null, "B", "B", "W"};
        al.add(new GamePlay(colors, results));
        when(game.getGamePlays()).thenReturn(al);

        when(gameRepository.getGameById(refEq(gameId, gameId.getId()))).thenReturn(game);

        exceptionRule.expect(OvercomeAttemptsException.class);
        GamePlayDto dto = gamePlayService.execute(request);
    }
}
