package com.buttons.mastermind.application.service;

public class ShowGameRequest {

    private String id;

    public ShowGameRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
