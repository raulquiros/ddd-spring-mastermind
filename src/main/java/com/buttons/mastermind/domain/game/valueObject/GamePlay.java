package com.buttons.mastermind.domain.game.valueObject;

public class GamePlay {

    private String[] colors;
    private String[] results;

    public GamePlay(String[] colors, String[] results) {
        this.colors = colors;
        this.results = results;
    }

    public String[] getColors() {
        return colors;
    }

    public String[] getResults() {
        return results;
    }

    public static GamePlay create(String color1, String color2, String color3, String color4)
    {
        return new GamePlay(new String[]{color1, color2, color3, color4}, new String[]{});
    }

    public void addResults(String[] results)
    {
        this.results = results;
    }
}
