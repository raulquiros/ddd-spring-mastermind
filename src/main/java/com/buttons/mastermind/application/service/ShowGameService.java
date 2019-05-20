package com.buttons.mastermind.application.service;

import com.buttons.mastermind.application.dto.GameDto;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.model.GameRepository;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowGameService {

    private final GameRepository gameRepository;

    @Autowired
    public ShowGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameDto execute(ShowGameRequest request) {

        GameId gameId = new GameId(request.getId());
        Game game = gameRepository.getGameById(gameId);

        return new GameDto(game);
    }
}
