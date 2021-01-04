package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

/**
 * Ant class constructs Ant objects.
 * Ants are movable game objects that can be steered by the user. 
 * Implements ISteerable. 
 * 
 * Ant also contains a single instance of Ant, which is enforced 
 * by the Singleton design pattern. 
 */
public class Ant extends MovableGameObject implements ISteerable {

	// Each ant has the following fields:
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	
	// Use this to call appropriate GameWorld collision methods. 
	private GameWorld gw;
	
	// Maintain a single global reference to the Ant - Singleton 
	private static Ant playerAnt;
	
	/**
	 * Ant constructor, inherits from MovableGameObject class. Also 
	 * initialized private fields of the class. Made private to follow
	 * Singleton design pattern 
	 * 
	 * @param x
	 * @param y
	 * @param heading
	 * @param speed
	 */
	private Ant(GameWorld gw, float x, float y, int heading, int speed) {
		super(100, x, y, 255, 0, 0, heading, speed);	
		this.maximumSpeed = 300;
		this.foodLevel = 1000;
		this.foodConsumptionRate = 2;	
		this.healthLevel = 10;
		this.lastFlagReached = 1;
		this.gw = gw;		
	}
	
	/**
	 * Provides access to the playerAnt, and creates it if necessary. Passes
	 * the same parameters as the private constructor to construct the ant as requested. 
	 * Made public static, logic follows Singleton Design pattern.
	 * 
	 * IF there is no playerAnt, create the ant with the passed parameters
	 * OTHERWISE, return the player ant
	 * 
	 * @param x
	 * @param y
	 * @param heading
	 * @param speed
	 * @return
	 */
	public static Ant getAnt(GameWorld gw, float x, float y, int heading, int speed) {
		if (playerAnt == null) 
			playerAnt = new Ant(gw, x, y, heading, speed); 	// create the Ant if not created already 
		return playerAnt;
	}
		
	/**
	 * Getter method for maximumSpeed. 
	 * @return maximumSpeed 
	 */
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}

	/**
	 * Setter method for maximumSpeed.
	 * @param maximumSpeed
	 */
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	/**
	 * Getter method for foodLevel
	 * @return foodLevel
	 */
	public int getFoodLevel() {
		return this.foodLevel;
	}

	/**
	 * Setter method for foodLevel
	 * @param foodLevel
	 */
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	/**
	 * Getter method for foodConsumptionRate 
	 * @return foodConsumptionRate
	 */
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	
	/**
	 * Setter method for foodConsumptionRate
	 * @param foodConsumptionRate
	 */
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	
	/**
	 * Getter method for healthLevel
	 * @return healthLevel
	 */
	public int getHealthLevel() {
		return this.healthLevel;
	}
	
	/**
	 * Setter method for healthLevel
	 * @param healthLevel
	 */
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	/**
	 * Getter method for lastFlagReached
	 * @return lastFlagReached
	 */
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}
	
	/**
	 * Setter method for lastFlagReached 
	 * @param lastFlagReached
	 */
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	/**
	 * Overridden method from MovableGameObject 
	 * Basic movements, since ant can be steered at any time by player. 
	 * 
	 * Convert (90 - heading) to radians. Take sine/cosine of this value, and 
	 * multiply it by the speed. 
	 * 
	 * Equation to calculate deltaX and deltaY given in assignment specifications. 
	 * Add deltaX and deltaY to current X and Y respectively to get the new 
	 * location coordinates. 
	 * 
	 * Ant is allowed to move out of the boundaries of the GameWorld, so no location 
	 * checks are needed. 
	 * 
	 * Then call setLocation. 
	 */
	@Override
	public void move(int elapsed, Dimension dCmpSize) {
		int dist = this.getSpeed() * elapsed/1000;
		
		float deltaX = (float) (Math.cos(Math.toRadians(90 - this.getHeading())) * dist);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - this.getHeading())) * dist);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		this.setLocation(newX, newY);
	}
	
	/**
	 * Steer method inherited from ISteerable interface, where player can 
	 * change the heading of the ant by adding or subtracting any value. 
	 * Method calculates the new heading based on the requested change, and 
	 * makes necessary conversions. 
	 * 
	 * If current heading + change is less than 0 or greater than 360, convert to equal heading 
	 * angle that is within 0-360. Otherwise, just add the change onto the current heading. 
	 */
	@Override
	public void steer(int h) {
		int leftover; 
		if (this.getHeading() + h >= 360) {
			leftover = ((this.getHeading() + h) - 360);
			this.setHeading(leftover);
		} else if (this.getHeading() + h <= 0) {
			leftover = ((this.getHeading() + h) + 360);
			this.setHeading(leftover);
		} else {
			this.setHeading(this.getHeading() + h);
		}
	}
	
	/**
	 * Overridden toString method to print Ant values for map. 
	 */
	@Override
	public String toString() {
		return "Ant: " + this.printLocation() + " " + this.printColor() + " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " " + this.printSize() + " maxSpeed=" + this.maximumSpeed + " foodConsumptionRate=" + this.foodConsumptionRate;
	}

	/**
	 * Draw method for the Ant object. Draws a filled circle. 
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelScrn) {
		g.setColor(this.getColor());
		g.fillArc((int) (pCmpRelScrn.getX() + this.getLocation().getX()), (int) (pCmpRelScrn.getY() + this.getLocation().getY()), this.getSize(), this.getSize(), 0, 360);
	}

	/**
	 * Method to handle the collision. Calls the appropriate GameWorld methods for each different ICollider object. Add to the collisions vector. 
	 */
	@Override
	public void handleCollision(GameObject otherObject) {
		if (!this.getCollisions().contains(otherObject) && !otherObject.getCollisions().contains(this)) {
			if (otherObject instanceof Spider) 
				gw.collideWithSpider();
			else if (otherObject instanceof Flag)
				gw.collideWithFlag((Flag) otherObject);
			else if (otherObject instanceof FoodStation)
				gw.collideWithFoodStation((FoodStation) otherObject);
			
			this.getCollisions().add(otherObject);
			otherObject.getCollisions().add(this);
		}
		
	}
	
}
