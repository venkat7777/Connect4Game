package com.connect4.controller;


import com.connect4.constants.Connect4GameConstants;
import com.connect4.service.Connect4GameService;
import com.connect4.exception.Connect4GameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


/**
 * Created by tempvabon on 2016-12-26.
 * is a rest controller that call service to save/update of players' game details,
 * calculate the winner of the game board and clear session.
 */
@RestController
@RequestMapping(value = Connect4GameConstants.PLAYER_REST_CONTROLLER)
public class PlayerController
{

    @Autowired
    private Connect4GameService gameService;

    /**
     * is a request mapping method save details of player
     * @param  playerName
     * @param  session
     */
    @RequestMapping(value = Connect4GameConstants.SAVE_PLAYER_DETAILS_MAPPING, method = { RequestMethod.PUT })
    @ResponseBody
    public void savePlayerGameDetails(@PathVariable(Connect4GameConstants.PLAYER_NAME) String playerName,
                                  HttpSession session)
    {
        try
        {
            gameService.savePlayerGameDetails(playerName, session);
        }
        catch (Connect4GameException e)
        {
             throw new Connect4GameException(Connect4GameConstants.PLAYER_DETAILS_CANNOT_SAVE);
        }
    }

    /**
     * is a request mapping method calculate the winner of board     *
     * @param   session
     *
     */

    @RequestMapping(value = Connect4GameConstants.GET_BOARD_WINNER_MAPPING, method = { RequestMethod.GET })
    @ResponseBody
    public String getBoardWinner(HttpSession session)
    {
        try
        {
            return gameService.getBoardWinningPlayer(session);
        }
        catch (Connect4GameException e)
        {
            throw new Connect4GameException(Connect4GameConstants.BOARD_WINNER_CANNOT_CALCULATED);
        }
    }

    /**
     * is a request mapping method refresh players' game details
     *  @param  session
     *
     *
     */

    @RequestMapping(value = Connect4GameConstants.REFRESH_PLAYER_DETAILS_MAPPING, method = { RequestMethod.GET })
    @ResponseBody
    public void refreshPlayerDetails(HttpSession session)
    {
        try
        {
            gameService.refreshPlayerGameDetails(session);
        }
        catch (Connect4GameException e)
        {
            throw new Connect4GameException(Connect4GameConstants.REFRESH_FAILED);
        }
    }
}
