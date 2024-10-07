/*
 * Ananya Raghunath
 * Final Project - Text Based RPG game
 * 12/7/23
 * This is an decisions rpg game
 * This is the main running file that controls the basis of the game!
 * */

import java.util.Scanner;

public class TextBasedGame {
	
	
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		boolean conti = false;
		String username;
		//makes them create a user name for recording purposes
		System.out.println("Welcome Player! Please create a username: ");
		username = in.nextLine();
		
		//Error trapping - checks if the username already exists
		conti = FileWork.checkingUser(username);
	    while (conti) {
	    	System.out.print("Invalid response. Username taken. Enter a new one:  ");
		    username = in.nextLine();
		    conti = FileWork.checkingUser(username);
	    }
		
		Player user = new Player (username); //creates a user object
		
		String cont = "Y";
		do {
			//Game menu 
			System.out.print("Welcome to the game main menu, please select from the following options:\n\t[1] Start Game\n\t[2] Show Scoreboard\n\t[0] Exit\n");
			int ans = in.nextInt();
		    while (ans != 1 && ans != 2 && ans != 0) //data validation
		    {
		    	System.out.print("Invalid response. Please type 1, 2, or 0: ");
			    ans = in.nextInt( );
		    }
			switch(ans) { //switch case used to run each portion of the menu 
			case 1:
				/*RPGgame rpgGame = new RPGgame();
			    rpgGame.buildStory();
			    rpgGame.playGame(user);*/
				RPGgame rpgGame = new RPGgame();
			    rpgGame.buildStory2();
			    rpgGame.playGame2(user);
			    break;
			case 2:
				FileWork.readingScoreFile();
				break;
			case 0:
				cont = "N"; //quits 
				break;
			}	
			
		} while (cont.startsWith("Y"));
   
	}
	
}