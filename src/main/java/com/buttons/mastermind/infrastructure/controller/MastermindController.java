package com.buttons.mastermind.infrastructure.controller;

import com.buttons.mastermind.application.dto.CreateGameDto;
import com.buttons.mastermind.application.dto.GameDto;
import com.buttons.mastermind.application.dto.GamePlayDto;
import com.buttons.mastermind.application.service.*;
import com.buttons.mastermind.domain.exception.InvalidColorsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/mastermind")
public class MastermindController
{

    @Autowired
    private CreateGameService createGameService;

    @Autowired
    private GamePlayService gamePlayService;

    @Autowired
    private ShowGameService showGameService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public CreateGameDto createGame(@RequestBody Map<String, Object> payload)
    {

        String color1 = payload.get("color1").toString();
        String color2 = payload.get("color2").toString();
        String color3 = payload.get("color3").toString();
        String color4 = payload.get("color4").toString();

        try {

            CreateGameRequest request = new CreateGameRequest(color1, color2, color3, color4);
            return createGameService.execute(request);

        } catch (InvalidColorsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public GamePlayDto gamePlay(@PathVariable("id") String id, @RequestBody Map<String, Object> payload)
    {
        String color1 = payload.get("color1").toString();
        String color2 = payload.get("color2").toString();
        String color3 = payload.get("color3").toString();
        String color4 = payload.get("color4").toString();

        GamePlayRequest request = new GamePlayRequest(id, color1, color2, color3, color4);

        return gamePlayService.execute(request);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GameDto gamePlay(@PathVariable("id") String id)
    {
        ShowGameRequest request = new ShowGameRequest(id);

        return showGameService.execute(request);
    }
}