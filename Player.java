/*
 * Ananya Raghunath
 * Final Project - Text Based RPG game
 * 12/7/23
 * This class hold the users info, their running points, their username, final score and their time
 * */

import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private String playerName;
    private int totalPoints;
    private String time;

    public Player(String playerName) {
        this.playerName = playerName;
        this.totalPoints = 0;
    }
    public Player(String playerName, int score, String time) {
        this.playerName = playerName;
        this.totalPoints = score;
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void addToTotalPoints(int points) {
        totalPoints += points;
    }
    public void setTime(String t) {
    	time = t;
    }
    public String getTime() {
    	return time;
    }
    
}
