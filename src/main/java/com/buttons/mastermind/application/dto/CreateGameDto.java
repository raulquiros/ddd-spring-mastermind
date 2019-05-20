package com.buttons.mastermind.application.dto;

import com.buttons.mastermind.domain.game.entity.Game;

public class CreateGameDto {

    private String id;
    private Integer attempts;

    public CreateGameDto(Game game) {
        this.id = game.getId().getId();
        this.attempts = game.getAttempts();

    }

    public String getId() {
        return id;
    }

    public Integer getAttempts() {
        return attempts;
    }
}
