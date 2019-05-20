package com.buttons.mastermind.domain.game.service;

import com.buttons.mastermind.domain.game.valueObject.GameId;

public interface IdGeneratorService {

    GameId generate();
}
