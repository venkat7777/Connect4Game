package com.connect4.exception;

/**
 * Created by tempvabon on 2016-12-26.
 * is a custom game exception class
 */
public class Connect4GameException extends  RuntimeException
{
    /**
     * @param message
     */
    public Connect4GameException(String message)
    {
        super(message);
    }
}
