/*
 * Ananya Raghunath
 * Final Project - Text Based RPG game
 * 12/7/23
 * This is the class that is used to run through the game decisions and sets up the game
 * */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RPGgame {
    private BinarySearchTreeNode<String> root;
    private Scanner scanner;

    RPGgame() {
    	
        root = null;
        scanner = new Scanner(System.in);
    }

    private BinarySearchTreeNode<String> insert(BinarySearchTreeNode<String> node, String data) {
        if (node == null) {
            return new BinarySearchTreeNode<String>(data);
        }

        System.out.println(node.getInfo()); // Display node content/story
        System.out.print("Your decision (Yes/No): ");
        String playerChoice = scanner.nextLine().toLowerCase();

        if (playerChoice.equals("1")) {
            node.left = insert(node.left, data + ", Yes");
        } else if ((playerChoice.equals("2"))){
            node.right = insert(node.right, data + ", No");
        }

        return node;
    }
    
    public void playGame2(Player user) { //This just controls the running of the game 
        BinarySearchTreeNode current = root;

        while (current != null && (current.left != null || current.right != null) ) { //(current.left != null || current.right != null)
        	Scanner in = new Scanner(System.in);
        	Dice twoSided = new Dice(2);
        	Dice thirtySides = new Dice(30);
        	String currentp = (String) current.getPoint();
        	int points = dieRoll(currentp);
        	user.addToTotalPoints(points);
            String playerChoice;
        	System.out.println(current.getInfo());
        	if(current.getType().equals("word")) { //if the scenario is one that requires a word answer it runs this portion
            	boolean cont = true;
            	System.out.print("Enter here: ");
    			 playerChoice = in.nextLine();
    		    while (cont)
    		    {
    		    	try 
            		{ 
            			
            			Integer.parseInt(playerChoice); 
            			System.out.println("Please enter a word: ");  //they can only enter a word to ensure that the game doesnt break 
            			playerChoice = in.nextLine();
            			cont = true;
            			
            			
            		}  
            		catch (NumberFormatException e)  
            		{ 
            			
            			cont = false;
            			
            		} 
    		    }
    			
            }
            else {
            System.out.print("Enter Here: ");
             playerChoice = scanner.nextLine().toLowerCase();
            }
	        //depending on if the answer type is word on not and according to the correct answer choice, the game sends the user to the left or right node    	
            if (current.getType().equals("word")) {
            	if(current.getAns().equals(playerChoice.toLowerCase())) {
            		current = current.left;
            	} else {
            		try 
            		{ 	
            			Integer.parseInt(playerChoice); 
            			current = current.right;
            			
            		}  
            		catch (NumberFormatException e)  
            		{ 
            			current = current.right; 
            			
            		} 
            	}
            	
            	
            }
            if (!current.getType().equals("word") || !(current.getType() == null)) {
	            if (playerChoice.equals("1")) {
	                current = current.left;
	            } else if(playerChoice.equals("2")){
	                current = current.right;
	            }
            }
        }
        
        
        //this prints out the ending scenario 
        if (current != null) {
            System.out.println("The result: " + current.getInfo());
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            	try {
        	    	FileWriter writer = new FileWriter("Players.txt", true);
        	        BufferedWriter out = new BufferedWriter(writer);
        	    	out.write(user.getPlayerName() + "\t" + user.getTotalPoints() + "\t" + currentTimestamp.toString());
        	    	out.newLine();
        	    	out.close();
            	} 
            	catch (IOException e) {
            		System.out.println("An error occurred.");
            		e.printStackTrace();
            	}
        } else {
        	//this portion writes to the file for organization and for the scofreboard 
        	Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        	try {
    	    	FileWriter writer = new FileWriter("Players.txt", true);
    	        BufferedWriter out = new BufferedWriter(writer);
    	    	out.write(user.getPlayerName() + "\t" + user.getTotalPoints() + "\t" + currentTimestamp.toString());
    	    	out.newLine();
    	    	out.close();
        	} 
        	catch (IOException e) {
        		System.out.println("An error occurred.");
        		e.printStackTrace();
        	}
        }
        
    }
/**
 * @param point
 * @return roll
 */
public int dieRoll(String point) {
	Dice thirtySides = new Dice(30);
	if (point == null) {
		return 0;
	}
	if (point.equals("")) {
		return 0;
	}
	if (point.equals("+")) {
		System.out.println("Looks like you are gaining points from you decision in the game. Now you will roll a dice to see how much you gained!");
		try {
	    	TimeUnit.SECONDS.sleep(1);
	    	System.out.println("Die Roll: ");
	    	int roll2 = thirtySides.roll();
	    	System.out.println("Rolling....");
	    	TimeUnit.SECONDS.sleep(1);
	    	System.out.println("You rolled: " + roll2);
	    	return (roll2);
	    	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (point.equals("-")) {
		System.out.println("Looks like you are losing points from you decision in the game. Now you will roll a dice to see how much you gained!");
		try {
	    	TimeUnit.SECONDS.sleep(1);
	    	System.out.println("Die Roll: ");
	    	int roll2 = thirtySides.roll();
	    	System.out.println("Rolling....");
	    	TimeUnit.SECONDS.sleep(1);
	    	System.out.println("You rolled: " + roll2);
	    	return (roll2*-1);
	    	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return 0;
}
public void buildStory2() { //Sets up the story 
    BinarySearchTreeNode<String> swamp; //see a monster
	BinarySearchTreeNode<String> fight; //choose between 2 weapons
	BinarySearchTreeNode<String> clear;
	BinarySearchTreeNode<String> trust;
	BinarySearchTreeNode<String> cont;
	BinarySearchTreeNode<String> right;
	BinarySearchTreeNode<String> investigate;
	BinarySearchTreeNode<String> move;
	BinarySearchTreeNode<String> wrong;
	BinarySearchTreeNode<String> r;
	BinarySearchTreeNode<String> wand;
	BinarySearchTreeNode<String> wai;
	BinarySearchTreeNode<String> inves;
	BinarySearchTreeNode<String> forward;
	BinarySearchTreeNode<String> w;
	BinarySearchTreeNode<String> converse;
	BinarySearchTreeNode<String> ri;
	BinarySearchTreeNode<String> listen;
	BinarySearchTreeNode<String> conti;
	BinarySearchTreeNode<String> wr;
	BinarySearchTreeNode<String> rig;
	BinarySearchTreeNode<String> wro;
	BinarySearchTreeNode<String> evade;
	BinarySearchTreeNode<String> forest;
	BinarySearchTreeNode<String> toward;
	BinarySearchTreeNode<String> eat;
	BinarySearchTreeNode<String> leav;
	BinarySearchTreeNode<String> in;
	BinarySearchTreeNode<String> flee;
	BinarySearchTreeNode<String> fig;
	BinarySearchTreeNode<String> ignore;
	BinarySearchTreeNode<String> c;
	BinarySearchTreeNode<String> wander;
	BinarySearchTreeNode<String> investi;
	BinarySearchTreeNode<String> n;
	BinarySearchTreeNode<String> stay;
	BinarySearchTreeNode<String> naur;
	BinarySearchTreeNode<String> wait;
	BinarySearchTreeNode<String> stand;
	BinarySearchTreeNode<String> figh;
	BinarySearchTreeNode<String> pickUp;
	BinarySearchTreeNode<String> explore;
	BinarySearchTreeNode<String> yes;
	BinarySearchTreeNode<String> naurr;
	BinarySearchTreeNode<String> avoid;
	BinarySearchTreeNode<String> gather;
	BinarySearchTreeNode<String> fi;
	BinarySearchTreeNode<String> fle;
	BinarySearchTreeNode<String> bypass;
	BinarySearchTreeNode<String> le;
	BinarySearchTreeNode<String> y;
	BinarySearchTreeNode<String> noo;
	BinarySearchTreeNode<String> no; //losss
	BinarySearchTreeNode<String> go; 
//These are all of the scenarios
	root = new BinarySearchTreeNode<String> ("You awaken and find yourself deep within an ancient, mysterious forest.\nThe towering trees block the sun, and the dense foliage obscures any sense of direction. \nYou must navigate through the forest, where every path seems to hold both promise and peril. \nThe forest suddenly opens up with 2 paths... \nOne path opens up into a serene clearing revealing glimpses of sunlight while the other path winds through ancient trees and dense undergrowth. \nWhich will you choose? [1] The clear path [2] The forested path: ");
	clear = new BinarySearchTreeNode<String> ("You chose to venture within the seemingly clear path. \nAs you proceed, you encounter creatures resembling forest spirits. \nWith no other direction, do you trust these spirits and follow their lead through the forest? [1] Yes [2] No");
		trust = new BinarySearchTreeNode<String> ("You chose to follow them, The spirits slowly guide you through the forest, revealing hidden paths and providing guidance.\n Your trust is rewarded with a sense of safety...\nSuddenly, they stop and a group of hostile creatures emerges from the shadows, blocking your path. \nDo you choose to fight them, relying on your skills, or attempt to evade the confrontation? [1] Fight [2] Evade", "+");
			fight = new BinarySearchTreeNode<String> ("You face the creatures head-on, utilizing your combat abilities to overcome the challenge...\nMost of the hostile creatures flee, and the spirits are amazed by your abilites...You continue to venture along you path.. \nAs you travel, you wonder into a lush green meadow where you must cross a bridge to continue. \nBut hidden under it, is a menacing troll. \nWill you continue along your path, or talk to the troll? [1] Continue [2] Converse ", "+");
				cont =  new BinarySearchTreeNode<String> ("You chose to proceed, however, the troll was not happy about that decision...\nHe stops you midway and asks you a riddle..\nI sleep curled up. I am every shade of the rainbow from red to blue to green to gold. \nI can eat a thousand sheep and still be hungry. What am I? ", "", "word", "dragon");
					right = new BinarySearchTreeNode<String> ("Congratulations! You got the riddle right! \nYou were allowed to pass. You continue on your path, when suddenly...you stumble upon a hidden pond, you notice a peculiar glow beneath the surface. \nWill you investigate the pond or move forward without interruption? [1] investigate [2] move on: ", "+");
						//investigate = new BinarySearchTreeNode <String> ("Listening to the whispers, you gain insights into the habits of nearby wildlife and potential dangers, aiding your journey.", "+");
						//move = new BinarySearchTreeNode <String> ("Choosing not to eavesdrop, you maintain your pace. The trees, though silent, seem to respect your decision, creating a serene atmosphere.");
					wrong = new BinarySearchTreeNode<String> ("Sadly your answer was incorrect, the troll has started to become very angry. \nHowever, to pass you must answer a riddle correctly. \nThe next riddle is:  I love to run but cannot walk. I love to babble but will never talk. \nWhat am I? (one word response)", "-", "word", "river");
						r = new BinarySearchTreeNode <String> ("Congratulations! You got the riddle right! You were allowed to pass. \nBut suddenly...dense fog envelops the trail, obscuring your vision....\nWill you wander blindly or wait for it to clear? [1] Wander [2] Wait:", "+");
							wand = new BinarySearchTreeNode <String> ("You wander, but you keep wandering? You are soon lost.", "-");
							wai = new BinarySearchTreeNode <String> ("Smart decision! You were able to wait out the fog! \nYou continue on your path, but you stumble upon a hidden pond, you notice a peculiar glow beneath the surface. \nWill you investigate the pond or move forward without interruption? [1] Investigate [2] Forward", "+");
								inves = new BinarySearchTreeNode <String> ("Examining the pond, you find glowing mushrooms. Their enticing glow makes you hungry. \nWill you pick them up and eat them or leave them? [1] Pick up [2] Leave them: ");		
									pickUp = new BinarySearchTreeNode <String> ("You chose to pick up the mushrooms, that was a smart choice! \nNot only are they perfect sources of light as you traverse the dark forest but they are also an excellent food source! \nUsing this new tool, you continue to venture along your path. Soon you come across a dilapidated shrine. \nWill you explore its mysteries or avoid it to save time? [1] explore [2] save time","+");
										explore =   new BinarySearchTreeNode <String> ("Investigating the shrine, you find a forgotten relic that wards off dark enchantments, proving beneficial in future encounters. \nHowever, picking up the relic has triggered some sort of trap within the shrine.\nIn panic you read the relic, you see the words \"Vesuvius\", \"Smoke\", \"Ash\", \"Mountain\", \"Yell what I am!\". \nWhat will you yell? (One word response)", "", "word", "volcano");
											yes = new BinarySearchTreeNode <String> ("You were correct! The guardians have allowed you to pass, it seems like you are in good hands. \nYou are ready to start a new and better adventure. ", "+");
											naurr = new BinarySearchTreeNode <String> ("You were sadly incorrect. \nDue to this a group of territorial creatures resembling guardians stand in your way. \nTheir eyes glow through the darkness with an otherworldly intensity. \nWill you attempt to converse with them or fight them?"); //stand & figh
										avoid =  new BinarySearchTreeNode <String> ("Choosing to bypass the shrine, you save time but risk encountering dark enchantments later without the protection of the relic. \nSoon after, the sun slowly rises. Revealing a lush area filled with rare flora. \nWill you gather these resources or bypass them in favor of speed? [1] gather [2] bypass: ", "-");
											gather = new BinarySearchTreeNode <String> ("You were able to gather increadible sources of food! \nHowever the time spent here has left you suceptible to an attack. Soon after you finished gathering, a large monster ambushes you. \nWill you fight it or flee? [1] Fight [2] Flee: ", "+");
												fi =  new BinarySearchTreeNode <String> ("You decided to fight the monster, but it seems that you have finally met your match. \nYour journey, unfortunately, ends here.", "-");
												fle =  new BinarySearchTreeNode <String> ("You tried to flee, but the monster was able to catch up in no time. \nIt was a great attempt but did not come to fruition. Looks like your journey ends here.");
											bypass = new BinarySearchTreeNode <String> ("You decided to bypass the shrine, not only did you miss out on valuble information, but you were also unable to navigate past the shrine. \nEach step you took was unfamiliar, but it felt like you were going in circles. You are trapped.\nUnfortunately your journey ends here.", "-");
									le = new BinarySearchTreeNode <String> ("You pass out on the mushrooms, but you have also missed out on an opportunity to navigate this forest better. \nWith no clear view, you stumble around...but...suddenly a group of territorial creatures resembling guardians stand in your way. \nTheir eyes glow through the darkness with an otherworldly intensity. \nWill you attempt to converse with them or fight them?"); //stand and figh
								forward = new BinarySearchTreeNode <String> ("You continue forward, but with no form of sustanence you are unable to continue this adventure");
						w = new BinarySearchTreeNode<String> ("Sadly your answer was incorrect. \nYou have lost the trolls and the spirits trust. \nYou are forever stuck in the forest.", "-");
				converse = new BinarySearchTreeNode<String> ("The troll was pleased by the fact that you asked, but to continue on your journey you MUST answer his riddle. \nI sleep curled up. I am every shade of the rainbow from red to blue to green to gold. \nI can eat a thousand sheep and still be hungry. \nWhat am I? (One word response) ", "+", "word", "dragon");				
					ri = new BinarySearchTreeNode<String> ("Congratulations! You got the riddle right! You were allowed to pass. \nSoon you delve into a forest, and you are greeted by a plethora of trees. \nA stand of tall pine trees rustles with a mysterious whisper. You sense they may hold valuable information. \nWill you listen closely or continue without stopping? [1] listen [2] continue: ", "+");
						listen = new BinarySearchTreeNode <String> ("Listening to the whispers, \nyou gain insights into the habits of nearby wildlife and potential dangers, aiding your journey.");
						conti = new BinarySearchTreeNode <String> ("Choosing not to eavesdrop, you maintain your pace. \nThe trees, though silent, seem to respect your decision, creating a serene atmosphere.");
					wr = new BinarySearchTreeNode<String> ("Sadly your answer was incorrect. But the troll is willing to give you a chance, since you so graciously conversed with him. \nTo pass you must answer a riddle correctly. \nThe next riddle is:  I love to run but cannot walk. I love to babble but will never talk. \nWhat am I? (one word response)", "", "word", "river");
						rig = new BinarySearchTreeNode <String> ("Congratulations! You got the riddle right! You were allowed to pass.", "+");
						wro = new BinarySearchTreeNode <String> ("You have lost the trolls trust. The spirits though loyal, have now grown impatient have left you. \nYou are left alone and forever stuck in the forest.", "-");
						wr.setRight(wro);
			evade = new BinarySearchTreeNode <String> ("You were able to successfully evade the creatures, however the spirits were unimpressed by your cowardice. \nThey have left you astray. Luckily, you find a way to a path, but from the corner of you eye you see a gleam. \nWill you go toward it, or continue along your path? [1] toward [2] continue: ", "-");
		no = new BinarySearchTreeNode <String> ("The spirits are upset by your decision, you have been sent to other path. \nBut as you arrive, you see a gleam from the corner of your eye. \nWill you go toward it or continue along your path?");
	forest = new BinarySearchTreeNode<String> ("You chose the forested path, as you arrive, you see a gleam from the corner of your eye. \nWill you go toward it, or continue along your path? [1] toward [2] continue");
		toward = new BinarySearchTreeNode <String> ("You come close and see mushrooms. \nThe mushrooms emit a soft, enchanting glow, enticing you with their mysterious allure. \nDo you choose to eat them, hoping for a potential energy boost, even though their effects are unknown? [1] Eat [2] Do not eat");
			eat = new BinarySearchTreeNode <String> ("The mushrooms induce vivid hallucinations, altering your perception of reality. Navigating the forest becomes challenging. \nIt seems as it is toxic as well, making it harder for you body to continue. ", "-");	
			leav = new BinarySearchTreeNode <String> ("Cautious of potential hallucinogenic or toxic effects, you decide to avoid eating the mushrooms and maintain your clarity in the darkening forest.\nSuddenly a gust of wind picks you up, and the next thing you know you are in campsite?!?! \nYou find a abandoned campsite covered in moss. The remains suggest a group was once here. \nWill you Investigate Carefully or ignore it and continue? [1] investigate it [2] continue", "+");	
				in = new BinarySearchTreeNode <String> ("You decide to investigate the campsite, searching for useful supplies among the decaying remnants of their journey. \nWhile searching, you disturb a hidden creature, prompting a tense encounter.\nWill you flee or fight with the tools in the campsite? [1] Flee [2] Fight ", "+");
					flee = new BinarySearchTreeNode <String> ("You save your life by fleeing, but you loose all your current supplies and any hope for surviving. Looks like your journey ends here.", "-");
					fig = new BinarySearchTreeNode <String> ("You chose to fight, it was the right choice! You were able to fend them off with the supplies within campsite! \nBut as you continue on your journey, you stumble upon guardians, who are blocking your path. \nWill you stand your ground and converse with them, or fight?", "+");
				ignore = new BinarySearchTreeNode <String> ("The campsite remains lifeless, and you press on without investigating. Your focus is on covering ground quickly.");
		c = new BinarySearchTreeNode <String> ("You continue further along you path, but as you move along... \nA dense fog envelops the trail, obscuring your vision....\nWill you wander blindly or wait for it to clear? [1] Wander [2] Wait: ");
			wander = new BinarySearchTreeNode <String> ("You decide to brave the fog and continue forward without a clear view. Within the fog, you hear mysterious sounds. \nDo you investigate, potentially discovering hidden wonders, or stay on your path? [1] Investigate [2] Stay on your unknown path: ");
				investi  = new BinarySearchTreeNode <String> ("The sounds become louder with each step...soon you are able to listen intently to the forest's secrets. \nThe whispers provide insights that aid your journey and survival. Soon you find that these whispers are cominf from spirits. \nWith no other direction, do you trust these spirits and follow their lead through the forest? [1] Yes [2] No", "+");
					n = new BinarySearchTreeNode <String> ("Without the spirits direction, you soon find yourself lost. Seems like your journey ends here.");
				stay = new BinarySearchTreeNode <String> ("The forest's whispers fade, and you continue your journey. Your decision to stay put may have cost you valuable information. \nBut as you continue, you encounter creatures resembling forest spirits. \nWith no other direction, do you trust these spirits and follow their lead through the forest? [1] Yes [2] No" , "-");	
					naur = new BinarySearchTreeNode <String> ("You chose to decline the spirits, they are upset with your decision. It seems like you continue to reject the forest and its inhabitants. \nSuddenly,territorial creatures resembling guardians stand in your way, almost as if they were responding to your countless rejections. \nTheir eyes glow with an otherworldly intensity. Will you stand your ground or fight them? ","-");
			wait = new BinarySearchTreeNode <String> ("Choosing caution, you decide to wait for the fog to clear before proceeding, hoping to maintain your bearings. Suddenly a group of a group of territorial creatures resembling guardians stand in your way. Their eyes glow with an otherworldly intensity. \nWill you attempt to converse with them or fight them? ");
				stand = new BinarySearchTreeNode <String> ("The guardians are pleased with you decision, but they must ensure that you are smart enough to take on the rest of the forest. You must answer the riddle correctly to pass. \nWhat five letter word stays the same/sounds the same when you take away the first, third, and last letter? ", "+", "word", "empty");	
					y = new BinarySearchTreeNode <String> ("Amazing! You got it right, you are incredibly capable of venturing this journey by yourself. \nYou continue to venture along safely and protected, starting new and better adventures!", "+");
					noo = new BinarySearchTreeNode <String> ("Though pleased with your previous decisions, the guardians have decided that this is the end of your journey for now. Maybe your next adventure will be much greater");
				figh = new BinarySearchTreeNode <String> ("The guardians are displeased with your actions, they are denying further passage into the forest. \nYou are now forever stuck in the depth of this realm", "-");	
///This sets all the correlations
root.setLeft(clear);
root.setRight(forest);

inves.setLeft(pickUp);
inves.setRight(le);
pickUp.setLeft(explore);
pickUp.setRight(avoid);
explore.setLeft(yes);
explore.setRight(naurr);
naurr.setLeft(stand);
naurr.setRight(figh);
avoid.setLeft(gather);
avoid.setRight(bypass);
gather.setLeft(fi);
gather.setRight(fle);
le.setLeft(stand);
le.setRight(figh);
stand.setLeft(y);
stand.setRight(noo);
fig.setLeft(stand);
fig.setRight(figh);

clear.setLeft(trust);
clear.setRight(no);
trust.setLeft(fight);
trust.setRight(evade);
evade.setLeft(toward);
evade.setRight(c);
no.setLeft(toward);
no.setRight(c);
fight.setLeft(cont);
fight.setRight(converse);
cont.setLeft(right);
cont.setRight(wrong);
right.setLeft(inves);
right.setRight(forward);
wrong.setLeft(r);
wrong.setRight(w);
r.setLeft(wand);
r.setRight(wait);
wai.setLeft(inves);
wai.setRight(forward);
converse.setLeft(ri);
converse.setRight(wr);
wr.setLeft(rig);
wr.setRight(wro);
ri.setLeft(listen);
ri.setRight(conti);
forest.setLeft(toward);
forest.setRight(c);
toward.setLeft(eat);
toward.setRight(leav);
leav.setLeft(in);
leav.setRight(ignore);
in.setLeft(flee);
in.setRight(fig);
c.setLeft(wander);
c.setRight(wait);
wander.setLeft(investi);
wander.setRight(stay);
investi.setLeft(eat);
investi.setRight(n);
wait.setLeft(stand);
wait.setRight(figh);
stay.setLeft(trust);
stay.setRight(naur);
naur.setLeft(stand);
naur.setRight(figh);
}


 public void buildStory() { //old story 
        BinarySearchTreeNode<String> swamp; //see a monster
    	BinarySearchTreeNode<String> fight; //choose between 2 weapons
    		BinarySearchTreeNode<String> stick;//use large stick
    		BinarySearchTreeNode<String> rock; //use rocks
    	BinarySearchTreeNode<String> runaway; //direction
    		BinarySearchTreeNode<String> left; //left
    			BinarySearchTreeNode<String> up; //left
    				BinarySearchTreeNode<String> back ; //back -- fall and loose
    				BinarySearchTreeNode<String> cont; //Continue
    			BinarySearchTreeNode<String> down; //left
    		BinarySearchTreeNode<String> right; //righ
    BinarySearchTreeNode<String> forest; //see a shining sword
    	BinarySearchTreeNode<String> pickUp; //leave it there or take it
    		BinarySearchTreeNode<String> leaveit;
    			BinarySearchTreeNode<String> l;
    				BinarySearchTreeNode<String> f;
	    				BinarySearchTreeNode<String> s;
	    					BinarySearchTreeNode<String> le;
	    					BinarySearchTreeNode<String> re;
	    				BinarySearchTreeNode<String> roc;
	    			BinarySearchTreeNode<String> run;
    			BinarySearchTreeNode<String> r;
    		BinarySearchTreeNode<String> takeit;
    	BinarySearchTreeNode<String> leave; //when leaving go left or right
    		BinarySearchTreeNode<String> leftt; //trip and fall into a sinkhole to ur demise
    		BinarySearchTreeNode<String> rightt; //find a berry bush
    			BinarySearchTreeNode<String> eat; //poisoned and die
    			BinarySearchTreeNode<String> leaveeit; //leave ut and find crown
    				BinarySearchTreeNode<String> pickup; //win
    				BinarySearchTreeNode<String> no; //losss
    root = new BinarySearchTreeNode<String> ("Welcome! Do you want to go to the swamp or the forest adventure? [1] Swap [2] Forest");
    	swamp = new BinarySearchTreeNode<String> ("You chose the swamp, but as you arrived you encountered a monster, will you fight or run? [1] fight [2] run");
    		fight = new BinarySearchTreeNode<String> ("You chose to fight, there are 2 weapons near you: a stick and a pile of rocks. Which will you choose? [1] stick [2] rocks");
    			stick = new BinarySearchTreeNode<String> ("You chose the stick, but the stick broke, and the monsted defeated you, leading to your untimely death... you lost the game....");
    			rock = new BinarySearchTreeNode<String> ("The stick broke, and the monsted defeated you, leading to your untimely death... you lost the game....");
    		runaway = new BinarySearchTreeNode<String> ("You chose to runaway, but which way will you go? [1] Left [2] Right");
	    		left = new BinarySearchTreeNode<String> ("You chose to go left, Great job! You escaped the monster by choosing to go left. But as you were running you fell into a cave, there are 2 paths, one where you climb up and one where you keep going deeper into the cave. Will you go up or down? [1] up [2] down");
	    			up = new BinarySearchTreeNode<String> ("You chose to go up! Although the climb up was tough, you managed to get to the top path. From there you were able to escape! But... you find yourself in the FOREST!?!?!? Will you go back in the cave in hopes of making your way back or will you venture into the forest?");
	    				back = new BinarySearchTreeNode<String> ("You chose to go back but, that was not a good idea, you fell into the deep dark abyss of the cave, and never made it out. You lost the game....");
	    				cont = new BinarySearchTreeNode<String> ("Congratulations, that was the end of this adventure! I hope you enjoy starting the next! You win!");
	    			down = new BinarySearchTreeNode<String> ("You chose to go down. However, that was not a good idea, you fell into the deep dark abyss of the cave, and never made it out. You lost the game....");
				right = new BinarySearchTreeNode<String> ("You chose to go left, but the monster was able to cut across the swamp, and attack you. You were defenseless and passed away... you lost the game....");
    	forest = new BinarySearchTreeNode<String> ("You chose the forest, as you arrive, from the corner of your eye you catch a glint of sunlight reflecting off an object. Upon further inspection you see a shimmering sword stuck in a stone. Will you attempt to take it or will you leave? [1] take [2] leave");
    		pickUp = new BinarySearchTreeNode<String> ("You chose to try to take out the sword from the stone, suprisingly it came out! But it seems quite suspicious, will continue to take it or will you leave it and walk away? [1] take it [2] leave it");
    			leaveit = new BinarySearchTreeNode<String> ("You chose to leave it and walk away, but which way will you go? [1] Left [2] Right");
	    			l = new BinarySearchTreeNode<String> ("You chose to go left, but as you were walking you encountered a monster, will you fight or run? [1] fight [2] run");
		        		f = new BinarySearchTreeNode<String> ("You chose to fight, there are 2 weapons near you: a stick and a pile of rocks. Which will you choose? [1] stick [2] rocks");
		        			s = new BinarySearchTreeNode<String> ("Luckily, you threw the stick in the opposite direction, which distracted the monster. Which way will you go to escape? [1] left [2] right");
		        				le = new BinarySearchTreeNode<String> ("You escaped this adventure, thank you for playing!");
		        				re = new BinarySearchTreeNode<String>  ("You ran too much and died of exhaustion, you lost the game!");
		        			roc = new BinarySearchTreeNode<String> ("The rocks were ineffective, leaving you defenseless. The monster defeated you, and you lost the game.");
		        		run = new BinarySearchTreeNode<String> ("You chose to runaway, but you had no idea where you were going, and amidst your panic you fell. The monster caught up and you died. Sadly, you lost the game...");
				    r = new BinarySearchTreeNode<String> ("You chose to go right. But that was not a good idea, you fell into a deep dark abyssmal cave, and never made it out. You lost the game....");
		        takeit = new BinarySearchTreeNode<String> ("You chose to take it, but sadly it was a trap. You unfortunatly fell to your demise... you lost the game...");
    		leave = new BinarySearchTreeNode<String> ("You chose to leave but which way will you go? [1] Left [2] Right");
    			leftt = new BinarySearchTreeNode<String> ("Trip and fall into a sinkhole, leading to your untimely demise, you lost....");
    			rightt = new BinarySearchTreeNode<String> ("You chose to go right, as you were walking you see bright red berries hanging off a bush. You realize that you are extremely hungry, will you eat it or leave it? [1] eat [2] leave");
    				eat = new BinarySearchTreeNode<String> ("You ate the berries but they were poisonous, you died and lost the game...");
    				leaveeit = new BinarySearchTreeNode<String> ("You chose to leave the berries behind, despite your hunger! As you were walking away, you see a crown from the corner of your eye. Will you pick it up or leave it be? [1] pick up [2] leave it");
    					pickup = new BinarySearchTreeNode<String> ("Congratulations, that was the secret to winning the game! Congrats!");
    					no = new BinarySearchTreeNode<String> ("You missed your final opportunity, sadly you lost!");
    root.setLeft(swamp);
    root.setRight(forest);
    swamp.setLeft(fight);
    swamp.setRight(runaway);
    fight.setLeft(stick);
    fight.setRight(rock);
    runaway.setLeft(left);
    runaway.setRight(right);
    left.setLeft(up);
    left.setRight(down);
    up.setLeft(back);
    up.setRight(cont);
    
    forest.setLeft(pickup);
    forest.setRight(leave);
    pickup.setLeft(leaveit);
    pickup.setRight(takeit);
    leaveit.setLeft(l);
    leaveit.setRight(r);
    l.setLeft(f);
    l.setRight(run);
    f.setLeft(s);
    f.setRight(roc);
    s.setLeft(le);
    s.setRight(re);
    leave.setLeft(leftt);
    leave.setRight(rightt);
    rightt.setLeft(eat);
    rightt.setRight(leaveeit);
    leaveeit.setLeft(pickup);
    leaveeit.setRight(no);
    }

    public void playGame(Player user) { //old game runner
        BinarySearchTreeNode current = root;

        while (current != null && (current.left != null || current.right != null)) {
        	Scanner in = new Scanner(System.in);
        	Dice twoSided = new Dice(2);
        	Dice thirtySides = new Dice(30);
        	
            System.out.println(current.getInfo());
            System.out.print("Enter Here: ");
            String playerChoice = scanner.nextLine().toLowerCase();
            if(current != root && current.getType() != "word") {
	            System.out.println("Do you want to roll the dice to get points (or loose them):");
				String ans = in.nextLine();
			    while (!ans.startsWith("Y") && !ans.startsWith("N")) {
			    	System.out.print("Invalid response. Please type Y or N: ");
				    ans = in.nextLine();
			    }
			    if (ans.startsWith("Y")) {
			    	System.out.println("There are 2 dice, 1 controls wether you gain or loose points and the other one controls the point amount.");
			    	try {
						TimeUnit.SECONDS.sleep(1);
				    	System.out.println("First Die Roll: ");
				    	int roll1 = twoSided.roll();
				    	System.out.println("Rolling....");
				    	TimeUnit.SECONDS.sleep(1);
				    	System.out.println("You rolled: " + roll1);
				    	if (roll1 == 1) {
				    		System.out.println("You lose points!");
				    	}
				    	else if (roll1 == 2) {
				    		System.out.println("You gain points!");
				    	}
				    	
				    	TimeUnit.SECONDS.sleep(1);
				    	System.out.println("Second Die Roll: ");
				    	int roll2 = thirtySides.roll();
				    	System.out.println("Rolling....");
				    	TimeUnit.SECONDS.sleep(1);
				    	System.out.println("You rolled: " + roll2);
				    	if (roll1 == 1) {
				    		System.out.println("You lose " + roll2 +" points!");
				    		user.addToTotalPoints(roll2*-1);
				    	}
				    	else if (roll2 == 2) {
				    		System.out.println("You gain " + roll2 + " points!");
				    		user.addToTotalPoints(roll2);
				    	}
				    	
			    	} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
		    }
            if (current.getType().equals("word")) {
            	if(current.getAns().equals(playerChoice.toLowerCase())) {
            		current = current.left;
            	} else {
            		current = current.right;
            	}
            	
            }
            if (!current.getType().equals("word")) {
	            if (playerChoice.equals("1")) {
	                current = current.left;
	            } else if(playerChoice.equals("2")){
	                current = current.right;
	            }
            } else {
            	
            }
        }

        if (current != null) {
            System.out.println("The result: " + current.getInfo());
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            user.setTime(currentTimestamp.toString());
        }
    }

}



   