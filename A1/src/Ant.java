package com.mycompany.a1;

public class Ant extends MovableGameObject implements ISteerable {

	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	
	public Ant(float x, float y, int heading, int speed) {
		super(20, x, y, 255, 0, 0, heading, speed);	
		this.maximumSpeed = 50;
		this.foodLevel = 20;
		this.foodConsumptionRate = 2;
		this.healthLevel = 10;
		this.lastFlagReached = 1;
	}
	
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getFoodLevel() {
		return this.foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	
	public int getHealthLevel() {
		return this.healthLevel;
	}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}

	@Override
	public void move(int heading, int speed) {
		float deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		this.setLocation(newX, newY);
	}
	
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
	
	@Override
	public String toString() {
		return "Ant: " + this.printLocation() + " " + this.printColor() + " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " " + this.printSize() + " maxSpeed=" + this.maximumSpeed + " foodConsumptionRate=" + this.foodConsumptionRate;
	}

}
