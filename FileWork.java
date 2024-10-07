/*
 * Ananya Raghunath
 * Final Project - Text Based RPG game
 * 12/7/23
 * This class reads out the score file to print out the score board (option in main menu),
 * and it is used when checking if a user name already exists (used in beginning of program)
 * */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class FileWork {

	public static void readingScoreFile() {
		BufferedReader reader;
		Scanner in = new Scanner(System.in);
		try {
			reader = new BufferedReader(new FileReader("Players.txt"));
			ArrayList<Player> playerRecords = new ArrayList<Player>();
			String currentLine = reader.readLine();
			while (currentLine != null)
			{
			       String[] playerDetail = currentLine.split("\t");
			       String name = playerDetail[0];
			       int score = Integer.valueOf(playerDetail[1]);
			       String time = playerDetail[2];
			       playerRecords.add(new Player(name, score, time));
			       currentLine = reader.readLine();
			}
			System.out.println("Do you want to see the score board by user or by score?\n\t[1] By User\n\t[2] By Score ");
			int ans = in.nextInt();
		    while (ans != 1 && ans != 2)
		    {
		    	System.out.print("Invalid response. Please type 1 or 2:");
			    ans = in.nextInt( );
		    }
		    if (ans == 1) {
		    	Collections.sort(playerRecords, new userCompare());
		    	
		    }
		    else if (ans == 2) {
		    	Collections.sort(playerRecords, new scoreCompare());
		    }
		    System.out.println("Scoreboard\n-------------------------------------------\nUsername\tScore\tTime\n-------------------------------------------");
	    	for (Player p : playerRecords) {
	    		System.out.println(p.getPlayerName() + "\t\t" + p.getTotalPoints() + "\t" + p.getTime());
	    	}
	    	System.out.println("");
	    	reader.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean checkingUser(String userr) {
		String user;
		user = userr;
		BufferedReader reader;
		Scanner in = new Scanner(System.in);
		try {
			reader = new BufferedReader(new FileReader("Players.txt"));
			ArrayList<Player> playerRecords = new ArrayList<Player>();
			String currentLine = reader.readLine();
			while (currentLine != null)
			{
			       String[] playerDetail = currentLine.split("\t");
			       String name = playerDetail[0];
			       int score = Integer.valueOf(playerDetail[1]);
			       String time = playerDetail[2];
			       playerRecords.add(new Player(name, score, time));
			       if(user.equals(name)) {
			    	   return true;
			       }
			       currentLine = reader.readLine();
			}
	    	reader.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

class userCompare implements Comparator<Player>{
	public int compare(Player p1, Player p2)
	{
	       return p1.getPlayerName().compareTo(p2.getPlayerName());
	}
}
class scoreCompare implements Comparator<Player>{
	public int compare(Player p1, Player p2)
	{
		 return p2.getTotalPoints()- p1.getTotalPoints();
	}

}
