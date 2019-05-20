package com.buttons.mastermind.domain.game.entity;

import com.buttons.mastermind.domain.game.valueObject.GameId;
import com.buttons.mastermind.domain.game.valueObject.GamePlay;

import java.util.ArrayList;

public class Game {

    public static final String COLOR_RED = "R";
    public static final String COLOR_GREEN = "G";
    public static final String COLOR_BLUE = "B";
    public static final String COLOR_YELLOW = "Y";
    public static final String COLOR_WHITE = "W";
    public static final String COLOR_PURPLE = "P";

    public static final String RESULT_BLACK = "B";
    public static final String RESULT_WHITE = "W";

    public static final Integer DEFAULT_ATTEMPTS = 15;

    private GameId id;
    private String[] colors;
    private ArrayList<GamePlay> gamePlays;
    private Integer attempts;

    Game(GameId id, String[] colors, ArrayList<GamePlay> gamePlays, Integer attempts) {
        this.id = id;
        this.colors = colors;
        this.gamePlays = gamePlays;
        this.attempts = attempts;
    }

    public GameId getId() {
        return id;
    }


    public String[] getColors() {
        return colors;
    }

    public ArrayList<GamePlay> getGamePlays() {
        return gamePlays;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public static Game create(GameId id, String color1, String color2, String color3, String color4) {
         return new Game(id, new String[]{color1, color2, color3, color4}, new ArrayList<>(), DEFAULT_ATTEMPTS);
    }
    public void addGamePlay(GamePlay gamePlay) {
        gamePlays.add(gamePlay);
        attempts--;
    }

    public void winGame() {
        attempts = 0;
    }
}
