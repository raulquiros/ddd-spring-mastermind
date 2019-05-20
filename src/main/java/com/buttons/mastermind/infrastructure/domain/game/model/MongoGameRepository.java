package com.buttons.mastermind.infrastructure.domain.game.model;

import com.buttons.mastermind.domain.exception.InvalidGameIdException;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.model.GameRepository;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class MongoGameRepository implements GameRepository {

    private final MongoOperations mongoOperations;

    @Autowired
    public MongoGameRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Game getGameById(GameId gameId) {
        Game game =  mongoOperations.findById(gameId, Game.class);
        if(game == null){
            throw new InvalidGameIdException();
        }

        return game;
    }

    @Override
    public void createGame(Game game)
    {
        mongoOperations.save(game);
    }

    @Override
    public void updateGame(Game game)
    {
        mongoOperations.save(game);
    }
}
