package com.buttons.mastermind.application.service;

import com.buttons.mastermind.application.dto.GamePlayDto;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.valueObject.GamePlay;
import com.buttons.mastermind.domain.game.model.GameRepository;
import com.buttons.mastermind.domain.game.service.CheckColorsService;
import com.buttons.mastermind.domain.game.service.ValidateColorsService;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayService {

    private final GameRepository gameRepository;
    private final ValidateColorsService validateColorsService;
    private final CheckColorsService checkColorsService;

    @Autowired
    public GamePlayService(GameRepository gameRepository, ValidateColorsService validateColorsService, CheckColorsService checkColorsService) {
        this.gameRepository = gameRepository;
        this.validateColorsService = validateColorsService;
        this.checkColorsService = checkColorsService;
    }

    public GamePlayDto execute(GamePlayRequest request) {

        GameId gameId = new GameId(request.getId());

        Game game = gameRepository.getGameById(gameId);

        GamePlay gamePlay = GamePlay.create(request.getColor1(), request.getColor2(), request.getColor3(), request.getColor4());

        validateColorsService.execute(request.getColor1(), request.getColor2(), request.getColor3(), request.getColor4());

        game.addGamePlay(gamePlay);
        String[] results = checkColorsService.execute(game);
        gamePlay.addResults(results);

        gameRepository.updateGame(game);

        return new GamePlayDto(game);
    }
}
