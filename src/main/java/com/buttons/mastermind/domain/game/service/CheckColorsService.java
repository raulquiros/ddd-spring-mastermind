package com.buttons.mastermind.domain.game.service;

import com.buttons.mastermind.domain.exception.OvercomeAttemptsException;
import com.buttons.mastermind.domain.game.entity.Game;
import com.buttons.mastermind.domain.game.valueObject.GamePlay;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CheckColorsService {

    public String[] execute(Game game)
    {
        ArrayList<GamePlay> gamePlays = game.getGamePlays();

        if(game.getAttempts()<= 0 || gamePlays.size() == Game.DEFAULT_ATTEMPTS - 1){
            throw new OvercomeAttemptsException();
        }

        GamePlay lastGamePlay = gamePlays.get(gamePlays.size() - 1);

        String[] result = new String[4];
        int i = 0;

        for (String color: lastGamePlay.getColors()) {

            int j = 0;
            loopGamePlayColor:
            for (String codemakerColor: game.getColors()) {

                if( j == i && color.equals(codemakerColor)){
                    result[i] = Game.RESULT_BLACK;
                    break loopGamePlayColor;
                }else if(j != i && color.equals(codemakerColor)) {
                    result[i] = Game.RESULT_WHITE;
                }

                j++;
            }

            i++;
        }

        boolean win = true;
        for (String resultEach: result) {
            if(resultEach == null || !resultEach.equals(Game.RESULT_BLACK)){
                win = false;
            }
        }
        if(win){
            game.winGame();
        }

        return result;
    }
}