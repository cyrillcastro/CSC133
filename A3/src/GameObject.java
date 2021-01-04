package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/**
 * GameObject classes constructs each GameObject for the game. Each GameObject has a 
 * size, location, and color, and each can be altered differently based on the object's 
 * role in the game. Because each GameObject has their own specifications, this class is abstract. 
 */
public abstract class GameObject implements IDrawable, ICollider {
	
	// private fields 
	private int size;
	private Point location;
	private int color;
	
	// Vector that keeps track of objects that this object collides with. 
	private Vector<GameObject> collisions;
	
	/**
	 * Constructor for the GameObject. Passes parameters for the size, 
	 * x and y for location, and r, g, b, for the color of the object. 
	 * 
	 * Passed size is set as the size of the GameObject. 
	 * Passed x and y are used to create a new point for the location. 
	 * Passed r, g, b are used to set the color with ColorUtil.rgb. 
	 * 
	 * @param size
	 * @param x
	 * @param y
	 * @param r
	 * @param g
	 * @param b
	 */
	public GameObject(int size, float x, float y, int r, int g, int b) {
		this.size = size;
		this.location = new Point(x, y);
		this.color = ColorUtil.rgb(r, g, b);
		
		this.collisions = new Vector<GameObject>();
	}
	
	/**
	 * Getter method for size
	 * @return size 
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Printer method for size. Useful in map printing. 
	 * @return value of size in string form 
	 */
	public String printSize() {
		return "size=" + this.getSize();
	}
	
	/**
	 * Getter method for location
	 * @return location 
	 */
	public Point getLocation() {
		return this.location;
	}
	
	/**
	 * Setter method for location. 
	 * @param x
	 * @param y
	 */
	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	/**
	 * Printer method for location - useful in printing map 
	 * @return x and y value of location in String form - use number formatting 
	 */
	public String printLocation() {
		return "loc=" + Math.round(this.location.getX() * 10.0) / 10.0 + "," + Math.round(this.location.getY() * 10.0) / 10.0;
	}
	
	/**
	 * Getter method for color
	 * @return color
	 */
	public int getColor() {
		return this.color;
	}
	
	/**
	 * Setter method for color 
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}
	
	/**
	 * Printer method for color - useful when printing map 
	 * @return r, g, b values of the GameObject color in the form of a String 
	 */
	public String printColor() {
		return "color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]";
	}
	
	/**
	 * Abstract toString method, which will be implemented in each of the specialized GameObjects. 
	 */
	public abstract String toString();
	
	/**
	 * Method to get the collisions vector for this object. 
	 * @return Vector<GameObject>
	 */
	public Vector<GameObject> getCollisions() {
		return this.collisions;
	}
	
	/**
	 * This method uses bounding squares to determine whether or not this object has collided with another.
	 * 
	 * Creates variables to hold the bounding points of the bounding square for each object.
	 * 
	 * Tests if there is Left/Right overlap or Top/Bottom overlap. If there is no overlap in one or the other,
	 * or BOTH, then there is no collision, return false. 
	 * 
	 * Else, check if the collisions vector has the object. If it does not, then add it to each of the vectors. 
	 * Change the result to true. 
	 * 
	 * Then check if the collisions vector contains the other object. If true, check if there is no overlap. 
	 * If that is also true, then result is false. Remove the objects from each vector. 
	 * 
	 * Return the result. 
	 */
	public boolean collidesWith(GameObject otherObject) {					
		float l1 = this.getLocation().getX() - this.getSize()/2;
		float r1 = this.getLocation().getX() + this.getSize()/2;
		float t1 = this.getLocation().getY() + this.getSize()/2;
		float b1 = this.getLocation().getY() - this.getSize()/2;
		
		float l2 = otherObject.getLocation().getX() - this.getSize()/2;
		float r2 = otherObject.getLocation().getX() + this.getSize()/2;
		float t2 = otherObject.getLocation().getY() + this.getSize()/2;
		float b2 = otherObject.getLocation().getY() - this.getSize()/2;
		
		// if there is no overlap, return false
		if (r1 < l2 || l1 > r2) 
			return false;
		else if (t2 < b1 || t1 < b2)		
			return false;
		else if ((r1 < l2 || l1 > r2) && (t2 < b1 || t1 < b2))
			return false;
		// otherwise, there is an overlap, so return true
		else
			return true;
	}

	/**
	 * Method to check if the current game object has the passed game object in its vector. If so, then remove. 
	 * @param otherObj
	 */
	public void handleNonCollision(GameObject otherObj) {
		if (this.collisions.contains(otherObj))
			this.collisions.remove(otherObj);		
	}
	
}
