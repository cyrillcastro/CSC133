package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * FoodStation class constructs FoodStation objects. FoodStations are fixed game objects
 * with a capacity.  
 */
public class FoodStation extends FixedGameObject {
	
	// private field exclusive for Food Stations
	private int capacity;
	
	// Use this to call the appropriate GameWorld method 
	private GameWorld gw; 

	/**
	 * FoodStation constructor, inherits from FixedGameObject. Also 
	 * instantiates capacity based on the size of the object. 
	 * 
	 * @param size
	 * @param x
	 * @param y
	 */
	public FoodStation(GameWorld gw, int size, float x, float y) {
		super(size, x, y, 0, 255, 0);	
		this.capacity = 2 * this.getSize();		
		this.gw = gw;
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

	/**
	 * Method to mark a Flag as selected or not 
	 */
	@Override
	public void setSelected(boolean yesNo) {
		super.setSelected(yesNo); 
	}

	/**
	 * Method that tests whether an object is selected 
	 */
	@Override
	public boolean isSelected() {
		return super.isSelected();
	}
	
	/**
	 * Method that determines if a pointer is in an object 
	 */
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)  {
		return super.contains(pPtrRelPrnt, pCmpRelPrnt);
	}
	

	/**
	 * Draw method for FoodStation. Draws a filled square and a string with its capacity. 
	 * 
	 * Should know about the different ways of drawing depending on "isSelected"
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelScrn) {
		int topLeftX = (int) this.getLocation().getX() - this.getSize()/2;
		int topLeftY = (int) this.getLocation().getY() - this.getSize()/2;
		
		g.setColor(this.getColor());
		
		if (this.isSelected() && gw.isPaused())
			g.drawRect((int) (pCmpRelScrn.getX() + topLeftX), (int) (pCmpRelScrn.getY() + topLeftY), this.getSize(), this.getSize());
		else {
			g.fillRect((int) (pCmpRelScrn.getX() + topLeftX), (int) (pCmpRelScrn.getY() + topLeftY), this.getSize(), this.getSize());
			this.setSelected(false);
		}
		
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.getCapacity(), (int) (pCmpRelScrn.getX() + topLeftX), (int) (pCmpRelScrn.getY() + topLeftY));

	}

	/**
	 * Method to handle collisions of various ICollider objects. Once done, add each object to the others' collision vectors
	 */
	@Override
	public void handleCollision(GameObject otherObject) {
		if (!this.getCollisions().contains(otherObject) && !otherObject.getCollisions().contains(this)) {
			if (otherObject instanceof Ant) 
				gw.collideWithFoodStation(this);
			
			this.getCollisions().add(otherObject);
			otherObject.getCollisions().add(this);
		}
	}
	
}