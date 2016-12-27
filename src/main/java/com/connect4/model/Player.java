package com.connect4.model;

/**
 * Created by tempvabon on 2016-12-26.
 * is a model class have the player details
 */
public class Player
{
    private String playerName;
    private int gamesWon;

    public Player(String playerName, int gamesWon)
    {
        this.playerName = playerName;
        this.gamesWon = gamesWon;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public int getGamesWon()
    {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon)
    {
        this.gamesWon = gamesWon;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (getGamesWon() != player.getGamesWon()) return false;
        return getPlayerName() != null ? getPlayerName().equals(player.getPlayerName()) : player.getPlayerName() == null;

    }

    @Override
    public int hashCode()
    {
        int result = getPlayerName() != null ? getPlayerName().hashCode() : 0;
        result = 31 * result + getGamesWon();
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", gamesWon=" + gamesWon +
                '}';
    }
}
