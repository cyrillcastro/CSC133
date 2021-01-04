package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable {
	
	// Used when random numbers are needed for whatever reason 
	private Random rand = new Random();
	
	// Game Object Collection to keep track of all of the game objects 
	GameObjectCollection gameObjColl;
	
	// GameWorld width and height - to be set to width and height of MapView  
	private int width;
	private int height;
	
	// Lives for the player to finish the game 
	private int lives = 3;
	
	// clock to keep track of how much time has passed 
	private int clock = 0;
	
	// boolean for sound of the game - initially set to false since checkbox is initially
	// not selected
	private boolean sound = false;
		
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

	/**
	 * GameWorld constructor - creates a new GameObjectCollection to collect all of the GameObjects for 
	 * keeping track. 
	 */
	public GameWorld() {
		gameObjColl = new GameObjectCollection();
	}
	
	/**
	 * Method to get the current GameObjectCollection. 
	 * @return gameObjColl
	 */
	public GameObjectCollection getGameObjColl() {
		return this.gameObjColl;
	}
	
	/**
	 * Getter method for width. 
	 * @return width 
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Setter method for width
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Getter method for height 
	 * @return height 
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Setter method for height 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Getter method for clock 
	 * @return clock 
	 */
	public int getClock() {
		return this.clock;
	}
	
	/**
	 * Getter method for flag value of sound 
	 * @return sound 
	 */
	public boolean getSound() {
		return this.sound;
	}
	
	/**
	 * Setter method for sound 
	 * @param sound
	 */
	public void setSound(boolean sound) {
		this.sound = sound;
		
		this.setChanged();
		this.notifyObservers();
		
		if (sound == true)
			System.out.println("Sound has been set on.");
		else 
			System.out.println("Sound has been set off.");
	}
	
	/**
	 * Getter method for lives 
	 * @return lives 
	 */
	public int getLives() {
		return this.lives;
	}
	
	/**
	 * Getter method for the last Flag Value
	 * @return lastFlagNum
	 */
	public int getLastFlagNum() {
		return this.lastFlagNum;
	}
	
	/**
	 * Getter method for player Ant - useful for ScoreView observer 
	 * @return player 
	 */
	public Ant getPlayer() {
		return this.player;
	}
	
	/**
	 * Creates and initializes the entire GameWorld (including the objects inside of it) 
	 */
	public void init() {		
		// Create 5 Flag objects and add them into the ArrayList - can change number of Flags later 
		firstFlag = new Flag((float) 502.1, (float) 394.9, 1);
		gameObjColl.add(firstFlag);
		secondFlag = new Flag((float) 629.7, (float) 409.2, 2);
		gameObjColl.add(secondFlag);
		thirdFlag = new Flag((float) 955.1, (float) 448.6, 3);
		gameObjColl.add(thirdFlag);
		fourthFlag = new Flag((float) 534.0, (float) 725.5, 4);
		gameObjColl.add(fourthFlag);
		fifthFlag = new Flag((float) 60.9, (float) 332.9, 5);
		gameObjColl.add(fifthFlag);
		
		// Set the value of the last flag 
		lastFlagNum = 5;
		
		// Create 1 Ant object and add it into the ArrayList
		// Place at same location as the Flag 
		player = player.getAnt((float) 502.1, (float) 394.9, 0, 7);
		gameObjColl.add(player);
		speedLimit = player.getMaximumSpeed();
		
		// Create 3 Spider objects and add them all into the ArrayList - can change number later 
		firstSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * width), (rand.nextFloat() * height), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjColl.add(firstSpider);
		secondSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * width), (rand.nextFloat() * height), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjColl.add(secondSpider);
		thirdSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * width), (rand.nextFloat() * height), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjColl.add(thirdSpider);
		
		//Create 3 FoodStation objects and add them all into the ArrayList - can change number later 
		firstStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(firstStation);
		secondStation = new FoodStation(rand.nextInt(61) + 10,(rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(secondStation);
		thirdStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(thirdStation);
	}
	
	/**
	 * Accelerates the speed of the player ant. 
	 * 
	 * Before setting the new speed of the ant, the program checks to make 
	 * sure that the ant would not be passing its speed limit. If it does, the 
	 * ant speeds up to its speed limit. If it does not, the speed of the ant increases by 2. 
	 * The ant moves according to its heading and new speed. 
	 * 
	 * Once this is complete, the GameWorld sets the changes and notifies its observers. 
	 */
	public void accelerate() {
		if (player.getSpeed() + 2 < speedLimit) 
			player.setSpeed(player.getSpeed() + 2);
		else if (player.getSpeed() + 2 >= speedLimit)
			player.setSpeed(speedLimit);
		player.move(player.getHeading(), player.getSpeed(), this.width, this.height);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Decreases the speed of the player ant. 
	 * 
	 * Before setting the new speed of the ant, the program checks to make 
	 * sure that the ant would not less than 0. If it does, the 
	 * ant slows down all the way to 0. If it does not, the speed of the ant decreases by 2. 
	 * The ant moves according to its heading and new speed. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void brake() {
		if (player.getSpeed() - 2 > 0)
			player.setSpeed(player.getSpeed() - 2);
		else if (player.getSpeed() - 2 <= 0)
			player.setSpeed(0);
		player.move(player.getHeading(), player.getSpeed(), this.width, this.height);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Turns the ant 5 degrees to the left. 
	 * 
	 * Calls steer method and passes -5. Ant moves according to new 
	 * heading and speed. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void turnLeft() {
		player.steer(-5);
		player.move(player.getHeading(), player.getSpeed(), this.width, this.height);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Turns the ant 5 degrees to the right. 
	 * 
	 * Calls steer method and passes +5. Ant moves according to new 
	 * heading and speed. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void turnRight() {
		player.steer(+5);
		player.move(player.getHeading(), player.getSpeed(), this.width, this.height);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Player collides with flag. 
	 * 
	 * First, the program checks if the flag that is reached is equal 
	 * to exactly one greater than the last flag reached. If so, the 
	 * player's lastFlagReached gets updated. 
	 * 
	 * Also checks if the lastFlagReached is the last flag of the game. If
	 * so, the program prints that the player has won, and the amount of time 
	 * it took to win. Then the game exits. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 * @param flagSeqNum
	 */
	public void collideWithFlag(int flagSeqNum) {
		if (player.getLastFlagReached() == flagSeqNum - 1) {
			player.setLastFlagReached(flagSeqNum);
			System.out.println("Player has reached Flag " + player.getLastFlagReached() + ".");
		}
		
		if (player.getLastFlagReached() == lastFlagNum) {
			System.out.println("\nGame over, you win! Total time: " + clock);
			System.exit(0);
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Player collides with food station. 
	 * 
	 * An ArrayList of the available food stations is created. A FoodStation object 
	 * to hold a randomly picked food station for use is created. Program iterates through
	 * the gameObjColl, has a holder GameObject to check if the current object is a FoodStation 
	 * with  capacity. If it does, then the object is added to the ArrayList. 
	 * 
	 * Then, the chosenStation gets filled with a random food station using rand. 
	 * 
	 * Finally, the player's foodLevel increases by the capacity of the chosen food station,
	 * the chosen food station's capacity gets set to 0, and the color of that food station fades. 
	 * Meanwhile, a new food station is created (with a random location) and gets added to the 
	 * gameObjColl. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void collideWithFoodStation() {
		ArrayList<FoodStation> availableStations = new ArrayList<FoodStation>();
		FoodStation chosenStation;
		
		IIterator theObjects = gameObjColl.getIterator();
		while (theObjects.hasNext()) {
			GameObject checkObject = theObjects.getNext();
			if (checkObject instanceof FoodStation && ((FoodStation) checkObject).getCapacity() > 0) 
				availableStations.add((FoodStation) checkObject);
		}

		chosenStation = availableStations.get(rand.nextInt(3));
			
		player.setFoodLevel(player.getFoodLevel() + chosenStation.getCapacity());
		chosenStation.setCapacity(0);
		chosenStation.setColor(175, 255, 175); 
		FoodStation newStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(newStation);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Player collides with a spider. Subtracts 1 from the healthLevel of the player ant. Sets the color and updates
	 * the speedLimit of the player ant based on its health level, and updates the currentSpeed if needed. 
	 * 
	 * Also checks for the current lives and the current health level. If healthLevel is 0, subtract one life. If there
	 * are no more lives, then Game Over. If there are still more lives, reset the health level and re-initialize the game. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void collideWithSpider() {
		player.setHealthLevel(player.getHealthLevel() - 1);
		
		int addFade = (int) 25.5 * (10 - (player.getHealthLevel()));
		player.setColor(255, addFade, addFade);
		
		int lowerSpeedBy = 5 * (10 - player.getHealthLevel());
		speedLimit = player.getMaximumSpeed() - lowerSpeedBy;
		
		if (player.getSpeed() > speedLimit)
			player.setSpeed(speedLimit);
		
		// if the health level is 0 
		if (player.getHealthLevel() == 0) {
			// subtract one life 
			lives--;
			// if there are no more lives, game over 
			if (lives == 0) {
				gameObjColl.clear();
				System.out.println("\nHealth level is 0. 1 life lost. Remaining lives: " + lives);
				System.out.println("\nGame over, you failed!");
				System.exit(0);
			// Otherwise, just restart. Clear the collection. Reset health. Reinitialize the game.  
			} else if (lives > 0) {
				gameObjColl.clear();
				System.out.println("\nHealth level is 0. 1 life lost. Remaining lives: " + lives);
				player.setHealthLevel(10);
				init();
			}
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Method that ticks the game clock. Iterate through the gameObjColl, have a GameObject holder to check
	 * if the current GameObject is a spider.
	 * The spiders then update heading at any value between -5 and 5. Iterate through the 
	 * gameObjColl to find all of the MovableGameObjects with the use of a GameObject holder to perform the checks. 
	 * All movable objects are told to update positions according to heading and speed. 
	 * 
	 * Ant's food level is reduced by ant indicated by foodConsumptionRate.
	 * Also checks for the current lives and the current health level. If healthLevel is 0, subtract one life. 
	 * If there are no more lives, then Game Over. If there are still more lives, reset the health level and 
	 * re-initialize the game. 
	 * 
	 * Finally, elapsed game clock is incremented by 1. 
	 * 
	 * Once this is done, GameWorld sets the changes and notifies its observers. 
	 */
	public void tickClock() {
		IIterator spiders = gameObjColl.getIterator();
		while (spiders.hasNext()) {
			GameObject checkObject = spiders.getNext();
			if (checkObject instanceof MovableGameObject && checkObject instanceof Spider) 
				((Spider) checkObject).setHeading(rand.nextInt(5 + 5) - 5);
		}
		
		IIterator movables = gameObjColl.getIterator();
		while (movables.hasNext()) {
			GameObject checkObject = movables.getNext();
			if (checkObject instanceof MovableGameObject) 
				((MovableGameObject) checkObject).move(((MovableGameObject) checkObject).getHeading(), ((MovableGameObject) checkObject).getSpeed(), this.width, this.height);
		}
		
		player.setFoodLevel(player.getFoodLevel() - player.getFoodConsumptionRate());
		
		if (player.getFoodLevel() == 0) {
			lives--;
			
			// if there are no more lives, game over 
			if (lives == 0) {
				gameObjColl.clear();
				System.out.println("\nFood level is 0. 1 life lost. Remaining lives: " + lives);
				System.out.println("\nGame over, you failed!");
				System.exit(0);
			// Otherwise, just restart. Clear the collection. Reset food level. Reinitialize the game.  
			} else if (lives > 0) {
				gameObjColl.clear();
				System.out.println("\nFood level is 0. 1 life lost. Remaining lives: " + lives);
				player.setFoodLevel(20);
				init();
			}
		}
		
		clock++;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Method that displays the current game and player values to the console. 
	 */
	public void display() {
		System.out.print("\nNumber of lives left: " + lives);
		System.out.print("\nElapsed time: " + clock);
		System.out.print("\nHighest flag value reached: " + player.getLastFlagReached());
		System.out.print("\nCurrent food level: " + player.getFoodLevel());
		System.out.print("\nCurrent health level: " + player.getHealthLevel());
		System.out.println();
	}
	
	/**
	 * Method that displays all of the GameObjects in the game in text form. Iterates
	 * through the gameObjectColl and uses the toString method to print out all GameObjects
	 * to the console. 
	 */
	public void outputMap() {
		IIterator theObjects = gameObjColl.getIterator();
		while (theObjects.hasNext()) {
			System.out.println(theObjects.getNext().toString());
		}
		
		System.out.println();
		
	}

}
