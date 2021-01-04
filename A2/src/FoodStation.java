package com.mycompany.a2;

/**
 * FoodStation class constructs FoodStation objects. FoodStations are fixed game objects
 * with a capacity.  
 */
public class FoodStation extends FixedGameObject {
	
	// private field exclusive for Food Stations
	private int capacity;

	/**
	 * FoodStation constructor, inherits from FixedGameObject. Also 
	 * instantiates capacity based on the size of the object. 
	 * 
	 * @param size
	 * @param x
	 * @param y
	 */
	public FoodStation(int size, float x, float y) {
		super(size, x, y, 0, 255, 0);	
		this.capacity = 2 * this.getSize();
	}
	
	/**
	 * Getter method for capacity
	 * @return capacity 
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Setter method for capacity
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Overridden toString method to print FoodStation values for map. 
	 */
	@Override
	public String toString() {
		return "FoodStation: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " capacity=" + this.capacity;
	}

}
