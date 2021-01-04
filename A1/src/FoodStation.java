package com.mycompany.a1;

public class FoodStation extends FixedGameObject {
	
	private int capacity;

	public FoodStation(int size, float x, float y) {
		super(size, x, y, 0, 255, 0);	
		this.capacity = 2 * this.getSize();
	}
	
	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "FoodStation: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " capacity=" + this.capacity;
	}

}
