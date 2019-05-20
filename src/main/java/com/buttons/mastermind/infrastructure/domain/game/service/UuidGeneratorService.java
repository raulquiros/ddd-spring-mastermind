package com.buttons.mastermind.infrastructure.domain.game.service;

import com.buttons.mastermind.domain.game.service.IdGeneratorService;
import com.buttons.mastermind.domain.game.valueObject.GameId;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidGeneratorService implements IdGeneratorService {

    @Override
    public GameId generate()
    {
        UUID uuid = UUID.randomUUID();

        return new GameId(uuid.toString());
    }
}
