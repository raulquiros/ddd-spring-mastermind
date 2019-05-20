package com.buttons.mastermind.domain.game.model;

import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.valueObject.GameId;

public interface GameRepository {

    Game getGameById(GameId gameId);

    void createGame(Game game);

    void updateGame(Game game);
}
