package com.buttons.mastermind.application.dto;

import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.valueObject.GamePlay;

public class GamePlayDto {

    private String color1;
    private String color2;
    private String color3;
    private String color4;
    private int attempts;



    public GamePlayDto(Game game) {

        GamePlay lastGamePlay = game.getGamePlays().get(game.getGamePlays().size() - 1);

        color1 = lastGamePlay.getResults()[0];
        color2 = lastGamePlay.getResults()[1];
        color3 = lastGamePlay.getResults()[2];
        color4 = lastGamePlay.getResults()[3];
        attempts = game.getAttempts();
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() {
        return color2;
    }

    public String getColor3() {
        return color3;
    }

    public String getColor4() {
        return color4;
    }

    public int getAttempts() {
        return attempts;
    }
}
