package com.buttons.mastermind.domain.game.service;

import com.buttons.mastermind.domain.exception.InvalidColorsException;
import com.buttons.mastermind.domain.game.entity.Game;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class ValidateColorsService {

    public void execute(String color1, String color2, String color3, String color4)
    {

        String[] validColors = {
                Game.COLOR_RED,
                Game.COLOR_GREEN,
                Game.COLOR_BLUE,
                Game.COLOR_YELLOW,
                Game.COLOR_WHITE,
                Game.COLOR_PURPLE
        };
        boolean color1Bool = Arrays.asList(validColors).contains(color1);
        boolean color2Bool = Arrays.asList(validColors).contains(color2);
        boolean color3Bool = Arrays.asList(validColors).contains(color3);
        boolean color4Bool = Arrays.asList(validColors).contains(color4);

        if(!color1Bool || !color2Bool || !color3Bool || !color4Bool){
            throw new InvalidColorsException();
        }
    }
}