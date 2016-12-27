package com.connect4.service;


import com.connect4.exception.Connect4GameException;
import javax.servlet.http.HttpSession;

/**
 * Created by tempvabon on 2016-12-26.
 *
 */
public interface Connect4GameService


{
    /**
     * is a service method calculate winner of the board
     *  @param   session
     *  @throws Connect4GameException
     */
     String getBoardWinningPlayer(HttpSession session) throws Connect4GameException;

    /**
     * is a service method save the game details of player
     *  @param   session
     *  @param playerName
     *  @throws Connect4GameException
     */
     void savePlayerGameDetails(String playerName, HttpSession session) throws Connect4GameException;

    /**
     * is a service method refresh session
     *  @param   session
     *  @throws Connect4GameException
     */

    void refreshPlayerGameDetails(HttpSession session) throws Connect4GameException;
}
