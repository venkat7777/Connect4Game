package com.connect4.service;

import com.connect4.constants.Connect4GameConstants;
import com.connect4.model.Player;
import com.connect4.exception.Connect4GameException;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tempvabon on 2016-12-26.
 * a service class  that save/update of players' game details,
 * calculate the winner of the game board and clear session
 */
@Service
public class Connect4GameServiceImpl implements Connect4GameService
{
    private static ConcurrentHashMap<String,Player> gameDetailsByPlayer =null;

    /**
     * is a service method calculates the board winner     *
     * @param   session
     */
    @Override
    public String getBoardWinningPlayer(HttpSession session) throws Connect4GameException
    {
    	String boardWinningPlayer = Connect4GameConstants.NO_PLAYER;
        if(session.getAttribute(Connect4GameConstants.GAME_DETAILS)!=null)
        {
            getPlayerGameDetailsFromSession(session);

            if(gameDetailsByPlayer.size() ==1)
            {
                Player player =gameDetailsByPlayer.get(gameDetailsByPlayer.keySet().toArray()[0]);
                boardWinningPlayer = player.getPlayerName();
            }
            else
            {
                int maxGames = Collections.max(gameDetailsByPlayer.values(),
                        (player1, player2) -> player1.getGamesWon() - player2.getGamesWon()).getGamesWon();

                int minGames = Collections.min(gameDetailsByPlayer.values(),
                        (player1, player2) -> player1.getGamesWon() - player2.getGamesWon()).getGamesWon();                

                if (maxGames == minGames)
                {
                    return boardWinningPlayer;
                } 
                else
                {
                    boardWinningPlayer = Collections.max(gameDetailsByPlayer.values(),
                            (player1, player2) -> player1.getGamesWon() - player2.getGamesWon()).getPlayerName();
                }
            }
        }
        return boardWinningPlayer;

    }

    /**
     * is a service method save/update game details of player
     * @param  playerName
     * @param   session
     */
    @Override
    public void savePlayerGameDetails(String playerName, HttpSession session) throws Connect4GameException
    {
        if(session.getAttribute(Connect4GameConstants.GAME_DETAILS) == null)
        {
            gameDetailsByPlayer = new ConcurrentHashMap<>();
            Player wonPlayer = new Player(playerName, Connect4GameConstants.ONE);
            updateGameDetails(playerName, session, wonPlayer);

        }
        else
        {
            getPlayerGameDetailsFromSession(session);
            if(gameDetailsByPlayer.get(playerName)==null)
            {
                Player wonPlayer = new Player(playerName,1);
                updateGameDetails(playerName, session, wonPlayer);
            }

            else
            {
                Player wonPlayer = gameDetailsByPlayer.get(playerName);
                wonPlayer.setGamesWon(wonPlayer.getGamesWon()+ Connect4GameConstants.ONE);
                updateGameDetails(playerName, session, wonPlayer);
            }

        }


    }

    /**
     * is  method get the details of Map from session
     * @param  session
     */
    private void getPlayerGameDetailsFromSession(HttpSession session)
    {
        gameDetailsByPlayer = (ConcurrentHashMap<String,Player>)session.getAttribute(Connect4GameConstants.GAME_DETAILS);
    }

    /**
     * is  service method to refresh the session
     * @param  session
     */
    @Override
    public void refreshPlayerGameDetails(HttpSession session) throws Connect4GameException
    {
        session.invalidate();
    }


    /**
     * is  method update the map in the session
     * @param   session
     * @param  wonPlayer
     */
    private void updateGameDetails(String playerName, HttpSession session, Player wonPlayer)
    {
        gameDetailsByPlayer.put(playerName,wonPlayer);
        session.setAttribute(Connect4GameConstants.GAME_DETAILS,gameDetailsByPlayer);
    }
}
