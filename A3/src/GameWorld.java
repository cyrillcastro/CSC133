package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class GameWorld extends Observable {
	
	// Used when random numbers are needed for whatever reason 
	private Random rand = new Random();
	
	// Game Object Collection to keep track of all of the game objects 
	private GameObjectCollection gameObjColl;
	
	// GameWorld width and height - to be set to width and height of MapView  
	private int width;
	private int height;
	
	// Lives for the player to finish the game 
	private int lives = 3;
	
	// clock to keep track of how much time has passed 
	private int clock = 0;
	
	// boolean for sound of the game - initially set to false since checkbox is initially
	// not selected
	private boolean sound = true;
	
	// boolean for pause mode of the game - initially set to false 
	private boolean pause;
		
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
	
	// Create and instantiate the sounds 
	private Sound collideSpider;
	private Sound eatFood;
	private Sound wrongFlag;
	private Sound correctFlag;
	private BGSound bgSound;
	
	private boolean switchPosition;

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
	public boolean soundOn() {
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
	 * Getter method for bgSound, useful for setSoundCommand
	 * @return bgSound
	 */
	public BGSound getBgSound() {
		return this.bgSound;
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
	 * Getter method for pause boolean
	 * @return pause
	 */
	public boolean isPaused() {
		return this.pause;
	}
	
	/**
	 * Setter method of pause boolean
	 * @param pause
	 */
	public void setPaused() {
		
		if (pause) {
			this.pause = false;
			System.out.println("Game has been set to play mode.");
		} else {
			this.pause = true;
			System.out.println("Game has been set to pause mode.");
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/** 
	 * Getter method for switch position boolean
	 * @return
	 */
	public boolean canSwitchPosition() {
		return this.switchPosition;
	} 
	
	/**
	 * Setter method for switch position boolean
	 */
	public void setSwitchPosition(boolean switchPosition) {
		this.switchPosition = switchPosition;
	}
	
	/**
	 * Creates and initializes the entire GameWorld (including the objects inside of it) 
	 * 
	 * Sets changes and notifies observers. 
	 */
	public void init() {	
		this.pause = false;
		
		//Create 3 FoodStation objects and add them all into the ArrayList - can change number later 
		firstStation = new FoodStation(this, rand.nextInt(101) + 85, (rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(firstStation);
		secondStation = new FoodStation(this, rand.nextInt(101) + 85,(rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(secondStation);
		thirdStation = new FoodStation(this, rand.nextInt(101) + 85, (rand.nextFloat() * width), (rand.nextFloat() * height));
		gameObjColl.add(thirdStation);
		
		// Create 5 Flag objects and add them into the ArrayList - can change number of Flags later 
		firstFlag = new Flag(this, (float) 1122.1, (float) 394.9, 1); 
		gameObjColl.add(firstFlag);
		secondFlag = new Flag(this, (float) 729.7, (float) 1009.2, 2);		
		gameObjColl.add(secondFlag);
		thirdFlag = new Flag(this, (float) 555.1, (float) 208.6, 3); 
		gameObjColl.add(thirdFlag);
		fourthFlag = new Flag(this, (float) 234.0, (float) 725.5, 4);
		gameObjColl.add(fourthFlag);
		fifthFlag = new Flag(this, (float) 1460.9, (float) 942.9, 5); 
		gameObjColl.add(fifthFlag);
		
		// Set the value of the last flag 
		lastFlagNum = 5;
		
		// Create 3 Spider objects and add them all into the ArrayList - can change number later 
		firstSpider = new Spider(this, rand.nextInt(76) + 70, (rand.nextFloat() * width), (rand.nextFloat() * height), rand.nextInt(360), rand.nextInt(66) + 60);
		gameObjColl.add(firstSpider);
		secondSpider = new Spider(this, rand.nextInt(76) + 70, (rand.nextFloat() * width), (rand.nextFloat() * height), rand.nextInt(360), rand.nextInt(66) + 60);
		gameObjColl.add(secondSpider);
		thirdSpider = new Spider(this, rand.nextInt(76) + 70, (rand.nextFloat() * width), (rand.nextFloat() * height), rand.nextInt(360), rand.nextInt(66) + 60);
		gameObjColl.add(thirdSpider);
		
		// Create 1 Ant object and add it into the ArrayList
		// Place at same location as the Flag 
		// set the Color of the Ant 
		// set the speed limit of the ant 
		player = player.getAnt(this, firstFlag.getLocation().getX(), firstFlag.getLocation().getY(), 0, 100);
		player.setColor(255, 0, 0);
		gameObjColl.add(player);
		speedLimit = player.getMaximumSpeed();
		
		collideSpider = new Sound("bugz-hitbyspider.wav");
		eatFood = new Sound("bugz-eatfood.wav");
		wrongFlag = new Sound("bugz-wrongflag.wav");
		correctFlag = new Sound("bugz-correctflag.wav");
		bgSound = new BGSound("bugz-background.wav");
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Accelerates the speed of the player ant. 
	 * 
	 * Before setting the new speed of the ant, the program checks to make 
	 * sure that the ant would not be passing its speed limit. If it does, the 
	 * ant speeds up to its speed limit. If it does not, the speed of the ant increases by 20a. 
	 * The ant then moves according to its heading and new speed. 
	 * 
	 * Once this is complete, the GameWorld sets the changes and notifies its observers. 
	 */
	public void accelerate() {
		if (player.getSpeed() + 20 < speedLimit) 
			player.setSpeed(player.getSpeed() + 20);
		else if (player.getSpeed() + 20 >= speedLimit)
			player.setSpeed(speedLimit);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Decreases the speed of the player ant. 
	 * 
	 * Before setting the new speed of the ant, the program checks to make 
	 * sure that the ant would not less than 0. If it does, the 
	 * ant slows down all the way to 0. If it does not, the speed of the ant decreases by 20. 
	 * The ant then moves according to its heading and new speed. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void brake() {
		if (player.getSpeed() - 20 > 0)
			player.setSpeed(player.getSpeed() - 20);
		else if (player.getSpeed() - 20 <= 0)
			player.setSpeed(0);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Turns the ant 10 degrees to the left. 
	 * 
	 * Calls steer method and passes -10. Ant then moves according to new 
	 * heading and speed. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void turnLeft() {
		player.steer(-10);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Turns the ant 10 degrees to the right. 
	 * 
	 * Calls steer method and passes +5. Ant moves according to new 
	 * heading and speed. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void turnRight() {
		player.steer(+10);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Player collides with flag. Returns whether or not the flag reached was the correct flag. 
	 * 
	 * First, the program checks if the flag that is reached is equal 
	 * to exactly one greater than the last flag reached. If so, play the correct sound the 
	 * player's lastFlagReached gets updated. 
	 * 
	 * Also checks if the lastFlagReached is the last flag of the game. If
	 * so, the program plays the appropriate sound and prints that the player has won, and the amount of time 
	 * it took to win. Then the game exits. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. Return true. 
	 * 
	 * Otherwise, play the wrong sound (not when the game starts) and print a message on the console saying that it's the wrong flag. Return false. 
	 * @param flagSeqNum
	 */
	public boolean collideWithFlag(Flag chosenFlag) {
		boolean result = false;
		if (player.getLastFlagReached() == chosenFlag.getSequenceNumber() - 1) {
			player.setLastFlagReached(chosenFlag.getSequenceNumber());
			System.out.println("Player has reached Flag " + player.getLastFlagReached() + ".");
			
			if (sound)
				correctFlag.play();
			
			if (player.getLastFlagReached() == lastFlagNum) {
				System.out.println("\nGame over, you win! Total time: " + clock);
				System.exit(0);
			}
			
			this.setChanged();
			this.notifyObservers();
			
			result = true;
		} else if (player.getLastFlagReached() != 1) {
			if (sound)
				wrongFlag.play();
			
			System.out.println("Wrong flag reached. Last flag reached was Flag " + player.getLastFlagReached() + ".");
		}
		
		return result;
	}
	
	/**
	 * Player collides with food station. 
	 * 
	 * Collided FoodStation, chosenStation, gets passed into the method. 
	 * 
	 * First, it checks if the capacity of the chosenStation is not empty. If it passes, then
	 * the player's foodLevel increases by the capacity of the chosen food station,
	 * the chosen food station's capacity gets set to 0, and the color of that food station fades. 
	 * Meanwhile, a new food station is created (with a random location) and gets added to the 
	 * gameObjColl. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void collideWithFoodStation(FoodStation chosenStation) {
		if (chosenStation.getCapacity() != 0) {
			player.setFoodLevel(player.getFoodLevel() + chosenStation.getCapacity());
			chosenStation.setCapacity(0);
			chosenStation.setColor(175, 255, 175); 
			FoodStation newStation = new FoodStation(this, rand.nextInt(101) + 80, (rand.nextFloat() * width), (rand.nextFloat() * height));
			gameObjColl.add(newStation);
			
			if (sound)
				eatFood.play();
			
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Player collides with a spider. Play the sound. Subtracts 1 from the healthLevel of the player ant. 
	 * 
	 * Calculates the factor of the ant's color fade. If the fade factor is greater than 255, then fade factor is set to 255. 
	 * 
	 * Sets the color and updates the speedLimit of the player ant based on its health level, and updates the currentSpeed if needed. 
	 * 
	 * Also checks for the current lives and the current health level. If healthLevel is 0, subtract one life. If there
	 * are no more lives, then game is over. If there are still more lives, reset the health level and re-initialize the game. 
	 * 
	 * Once this is done, the GameWorld sets the changes and notifies its observers. 
	 */
	public void collideWithSpider() {
		player.setHealthLevel(player.getHealthLevel() - 1);
		
		int addFade = (int) 26 * (10 - (player.getHealthLevel()));
		
		if (addFade > 255)
			addFade = 255;
		
		player.setColor(255, addFade, addFade);
		
		int lowerSpeedBy = 5 * (10 - player.getHealthLevel());
		speedLimit = player.getMaximumSpeed() - lowerSpeedBy;
		
		if (player.getSpeed() > speedLimit)
			player.setSpeed(speedLimit);
		
		if (sound) 
			collideSpider.play();
		
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
				player.setFoodLevel(1000);
				init();
			}
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Method that ticks the game clock if the game is not on pause. 
	 * 
	 * Play the sounds if the sounds are on. 
	 * 
	 * Iterate through the gameObjColl, have a GameObject holder to check if the current GameObject is a spider.
	 * The spiders then update heading by adding any value between -5 and 5. Then iterate through the 
	 * gameObjColl to find all of the MovableGameObjects with the use of a GameObject holder to perform the checks. 
	 * All movable objects are told to update positions according to heading and speed. 
	 * 
	 * Once all of the objects have moved, checked to see if any collisions have occurred and handle them appropriately. 
	 * 
	 * Ant's food level is reduced by ant indicated by foodConsumptionRate.
	 * Also checks for the current lives and the current health level. If healthLevel is 0, subtract one life. 
	 * If there are no more lives, then Game Over. If there are still more lives, reset the health level and 
	 * re-initialize the game. 
	 * 
	 * Elapsed game clock is incremented by 1.
	 * 
	 * Once this is done, GameWorld sets the changes and notifies its observers. 
	 */
	public void tickClock(int elapsed, Dimension dCmpSize) {	
		if (!isPaused()) {
			
			IIterator spiders = gameObjColl.getIterator();
			while (spiders.hasNext()) {
				GameObject checkObject = spiders.getNext();
				if (checkObject instanceof Spider) 
					((Spider) checkObject).setHeading(((MovableGameObject) checkObject).getHeading() + rand.nextInt(5 + 5) - 5);
			}
			
			IIterator movables = gameObjColl.getIterator();
			while (movables.hasNext()) {
				GameObject checkObject = movables.getNext();
				if (checkObject instanceof MovableGameObject) 
					((MovableGameObject) checkObject).move(elapsed, dCmpSize);
			}
			
			IIterator collideIter1 = gameObjColl.getIterator();
			while (collideIter1.hasNext()) {
				GameObject curObj = collideIter1.getNext();
				
				IIterator collideIter2 = gameObjColl.getIterator();
				while (collideIter2.hasNext()) {
					GameObject otherObj = collideIter2.getNext();
					
					if (curObj != otherObj) {
						if (curObj.collidesWith(otherObj)) 
							curObj.handleCollision(otherObj);
						else {
							curObj.handleNonCollision(otherObj);
						}
					}
				}
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
					player.setFoodLevel(1000);
					init();
				}
			}
			clock++;
			
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Method to activate the position function on the selected FixedGameObject 
	 * 
	 * If the game is paused, then find if an object is selected. If so, then the position functionality
	 * is activated.  
	 */
	public void reposition() {
		if (this.isPaused()) {
			IIterator findSelected = gameObjColl.getIterator();
			
			while (findSelected.hasNext()) {
				GameObject hold = findSelected.getNext();
				
				if (hold instanceof FixedGameObject && ((FixedGameObject) hold).isSelected()) {
					this.switchPosition = true;
					System.out.println("Position functionality activated.");
					break;
				}
			}
		}
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
