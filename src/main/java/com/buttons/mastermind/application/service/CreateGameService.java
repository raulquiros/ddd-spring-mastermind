package com.buttons.mastermind.application.service;

import com.buttons.mastermind.application.dto.CreateGameDto;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.model.GameRepository;
import com.buttons.mastermind.domain.game.service.IdGeneratorService;
import com.buttons.mastermind.domain.game.service.ValidateColorsService;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateGameService {

    private final GameRepository gameRepository;
    private final IdGeneratorService idGeneratorService;
    private final ValidateColorsService validateColorsService;

    @Autowired
    public CreateGameService(GameRepository gameRepository, IdGeneratorService idGeneratorService, ValidateColorsService validateColorsService) {
        this.gameRepository = gameRepository;
        this.idGeneratorService = idGeneratorService;
        this.validateColorsService = validateColorsService;
    }

    public CreateGameDto execute(CreateGameRequest request) {

        GameId gameId = idGeneratorService.generate();

        validateColorsService.execute(request.getColor1(), request.getColor2(), request.getColor3(), request.getColor4());

        Game game = Game.create(
                gameId,
                request.getColor1(),
                request.getColor2(),
                request.getColor3(),
                request.getColor4()
        );

        gameRepository.createGame(game);

        return new CreateGameDto(game);
    }
}
