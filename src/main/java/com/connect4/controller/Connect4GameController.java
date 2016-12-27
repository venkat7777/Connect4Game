package com.connect4.controller;

import com.connect4.constants.Connect4GameConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by tempvabon on 2016-12-25.
 * is a controller class of start of the game page
 */
@Controller
public class Connect4GameController
{
    /**
     * is a request mapping method returns to start game page
     * @param  model
     * @return String
     */
    @RequestMapping(Connect4GameConstants.START_GAME_REQUEST_MAPPING)
    public String startGame(Model model)
    {
        return Connect4GameConstants.START_GAME_PAGE;
    }

}
