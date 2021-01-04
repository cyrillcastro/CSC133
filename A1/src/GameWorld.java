package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
	
	private Random rand = new Random();
	private ArrayList<GameObject> gameObjCollection;
	private static final float GAME_WORLD_WIDTH = 1024;
	private static final float GAME_WORLD_HEIGHT = 768;
	private int lives = 3;
	private int clock = 0;
	
	// Create player ant object for the class manipulation, initialize in init() method
	private Ant player;
	
	// Speed limit of the ant - initially set to max speed after Ant is created
	private int speedLimit;
	
	// Last flag number
	private int lastFlagNum;
	
	// Create the rest of the objects
	private Flag firstFlag;
	private Flag secondFlag;
	private Flag thirdFlag;
	private Flag fourthFlag;
	private Flag fifthFlag;
	private Spider firstSpider;
	private Spider secondSpider;
	private Spider thirdSpider;
	private FoodStation firstStation;
	private FoodStation secondStation;
	private FoodStation thirdStation;

	public GameWorld() {
		gameObjCollection = new ArrayList<GameObject>();
	}
	
	public void init() {		
		// Create 5 Flag objects and add them into the ArrayList - can change number of Flags later 
		firstFlag = new Flag((float) 502.1, (float) 394.9, 1);
		gameObjCollection.add(firstFlag);
		secondFlag = new Flag((float) 629.7, (float) 409.2, 2);
		gameObjCollection.add(secondFlag);
		thirdFlag = new Flag((float) 955.1, (float) 448.6, 3);
		gameObjCollection.add(thirdFlag);
		fourthFlag = new Flag((float) 534.0, (float) 725.5, 4);
		gameObjCollection.add(fourthFlag);
		fifthFlag = new Flag((float) 60.9, (float) 332.9, 5);
		gameObjCollection.add(fifthFlag);
		
		lastFlagNum = 5;
		
		// Create 1 Ant object and add it into the ArrayList
		// Place at same location as the Flag 
		player = new Ant((float) 502.1, (float) 394.9, 0, 7);
		gameObjCollection.add(player);
		speedLimit = player.getMaximumSpeed();
		
		// Create 3 Spider objects and add them all into the ArrayList - can change number later 
		firstSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjCollection.add(firstSpider);
		secondSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjCollection.add(secondSpider);
		thirdSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjCollection.add(thirdSpider);
		
		//Create 3 FoodStation objects and add them all into the ArrayList - can change number later 
		firstStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT));
		gameObjCollection.add(firstStation);
		secondStation = new FoodStation(rand.nextInt(61) + 10,(rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT));
		gameObjCollection.add(secondStation);
		thirdStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT));
		gameObjCollection.add(thirdStation);
	}

	public void exit() {
		System.out.println("\nAre you sure you want to exit the game? Y/N: ");
	}
	
	public void accelerate() {
		if (player.getSpeed() + 2 < speedLimit) 
			player.setSpeed(player.getSpeed() + 2);
		else if (player.getSpeed() + 2 >= speedLimit)
			player.setSpeed(speedLimit);
		player.move(player.getHeading(), player.getSpeed());
	}
	
	public void brake() {
		if (player.getSpeed() - 2 > 0)
			player.setSpeed(player.getSpeed() - 2);
		else if (player.getSpeed() - 2 <= 0)
			player.setSpeed(0);
		player.move(player.getHeading(), player.getSpeed());
	}
	
	public void turnLeft() {
		player.steer(-5);
		player.move(player.getHeading(), player.getSpeed());
	}
	
	public void turnRight() {
		player.steer(+5);
		player.move(player.getHeading(), player.getSpeed());
	}
	
	public void collideWithFlag(int flagSeqNum) {
		if (player.getLastFlagReached() == flagSeqNum - 1)
			player.setLastFlagReached(flagSeqNum);
		
		if (player.getLastFlagReached() == lastFlagNum) {
			System.out.println("\nGame over, you win! Total time: " + clock);
			System.exit(0);
		}
	}
	
	public void collideWithFoodStation() {
		ArrayList<FoodStation> availableStations = new ArrayList<FoodStation>();
		FoodStation chosenStation;
		
		for (GameObject g : gameObjCollection) {
			if (g instanceof FoodStation && ((FoodStation) g).getCapacity() != 0) {
				availableStations.add((FoodStation) g);
			}
		}
		
		chosenStation = availableStations.get(rand.nextInt(3));
			
		player.setFoodLevel(chosenStation.getCapacity());
		chosenStation.setCapacity(0);
		chosenStation.setColor(175, 255, 175); 
		FoodStation newStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * GAME_WORLD_WIDTH), (rand.nextFloat() * GAME_WORLD_HEIGHT));
		gameObjCollection.add(newStation);
	}
	
	public void collideWithSpider() {
		player.setHealthLevel(player.getHealthLevel() - 1);
		player.setColor(255, 255 * (player.getHealthLevel() / 10), 255 * (player.getHealthLevel() / 10));
		
		speedLimit = player.getMaximumSpeed() * (player.getHealthLevel() / 10);
		
		if (player.getSpeed() > speedLimit)
			player.setSpeed(speedLimit);
		
		// if the health level is 0 
		if (player.getHealthLevel() == 0) {
			// subtract one life 
			lives--;
			// if there are no more lives, game over 
			if (lives == 0) {
				gameObjCollection.clear();
				System.out.println("\nHealth level is 0. 1 life lost. Remaining lives: " + lives);
				System.out.println("\nGame over, you failed!");
				System.exit(0);
			// Otherwise, just restart 
			} else if (lives > 0) {
				gameObjCollection.clear();
				System.out.println("\nHealth level is 0. 1 life lost. Remaining lives: " + lives);
				init();
			}
		}
	}
	
	public void tickClock() {
		// Spiders update heading
		for (GameObject g : gameObjCollection) {
			if (g instanceof MovableGameObject && g instanceof Spider) 
				((MovableGameObject) g).setHeading(rand.nextInt(5 + 5) - 5);
		}
		
		// all movable objects are told to update positions 
		// according to heading and speed
		for (GameObject g : gameObjCollection) {
			if (g instanceof MovableGameObject) {
				((MovableGameObject) g).move(((MovableGameObject) g).getHeading(), ((MovableGameObject) g).getSpeed());
			}
		}
		
		// ants food level is reduced by ant indicated by foodConsumptionRate
		player.setFoodLevel(player.getFoodLevel() - player.getFoodConsumptionRate());
		
		// elapsed game clock is implement by 1 
		clock++;
	}
	
	public void display() {
		System.out.print("\nNumber of lives left: " + lives);
		System.out.print("\nElapsed time: " + clock);
		System.out.print("\nHighest flag value reached: " + player.getLastFlagReached());
		System.out.print("\nCurrent food level: " + player.getFoodLevel());
		System.out.print("\nCurrent health level: " + player.getHealthLevel());
	}
	
	public void outputMap() {
		for (GameObject g : gameObjCollection) 
			System.out.println(g.toString());
	}

}
