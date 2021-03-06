package com.buttons.mastermind.application.service;

public class GamePlayRequest {

    private String id;
    private String color1;
    private String color2;
    private String color3;
    private String color4;

    public GamePlayRequest(String id, String color1, String color2, String color3, String color4) {
        this.id = id;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public String getId() {
        return id;
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
}
