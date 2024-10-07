/*
 * Ananya Raghunath
 * Final Project - Text Based RPG game
 * 12/7/23
 * This is a simple dice class, used for the points
 * */


import java.util.Random;

public class Dice {
    private final int sides;

    public Dice(int num) {
        sides = num;
    }

    public int roll() {
    	Random random = new Random();
        return random.nextInt(sides) + 1;
    }
}
