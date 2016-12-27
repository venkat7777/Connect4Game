package com.connect4.constants;

/**
 * Created by tempvabon on 2016-12-26.
 * a class that have all the constants of the application
 */

public class Connect4GameConstants
{
    public static final String START_GAME_REQUEST_MAPPING         =  "/startConnect4Game";
    public static final String START_GAME_PAGE                    =  "gameStart";
    public static final String PLAYER_REST_CONTROLLER             =  "/player";
    public static final String SAVE_PLAYER_DETAILS_MAPPING        =  "/details/{playerName}";
    public static final String PLAYER_DETAILS_CANNOT_SAVE         =  "Failed to save or update player game details";
    public static final String GET_BOARD_WINNER_MAPPING           =  "/getBoardWinner";
    public static final String BOARD_WINNER_CANNOT_CALCULATED     =  "Failed to calculate board winner";
    public static final String REFRESH_PLAYER_DETAILS_MAPPING     =  "/refreshGame";
    public static final String REFRESH_FAILED                     =  "Failed to refresh";
    public static final String NO_PLAYER                          =  "-1";
    public static final String GAME_DETAILS                       =  "gameDetails";
    public static final String PLAYER_NAME                        =   "playerName";
    public static final int ONE                                   =   1;



}
