package com.buttons.mastermind.application.dto;

import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.valueObject.GamePlay;

import java.util.ArrayList;
import java.util.List;

public class GameDto {

    private String color1;
    private String color2;
    private String color3;
    private String color4;

    private ArrayList<String[]> colors;
    private ArrayList<String[]> results;


    private int attempts;



    public GameDto(Game game) {

        this.color1 = game.getColors()[0];
        this.color2 = game.getColors()[1];
        this.color3 = game.getColors()[2];
        this.color4 = game.getColors()[3];

        this.colors = new ArrayList<>();
        this.results = new ArrayList<>();

        for (GamePlay gp: game.getGamePlays()) {

            this.colors.add(gp.getColors());
            this.results.add(gp.getResults());
        }


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

    public ArrayList<String[]> getColors() {
        return colors;
    }

    public ArrayList<String[]> getResults() {
        return results;
    }
}
