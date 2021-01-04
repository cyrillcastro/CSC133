package com.mycompany.a2;

/**
 * MovableGameObject class constructs movable game objects. Movable game objects are 
 * game objects that change their location based on two components - heading and speed. 
 *  Because each MovableGameObject has their own specifications, this class is abstract.
 */
public abstract class MovableGameObject extends GameObject {
	
	// private fields 
	private int heading;
	private int speed;
	
	/**
	 * MovableGameObject constructor. Inherits from GameObject constructor, and also instantiates 
	 * heading and speed based on the values that are passed. 
	 * 
	 * @param size
	 * @param x
	 * @param y
	 * @param r
	 * @param g
	 * @param b
	 * @param heading
	 * @param speed
	 */
	public MovableGameObject(int size, float x, float y, int r, int g, int b, int heading, int speed) {
		super(size, x, y, r, g, b);
		this.heading = heading;
		this.speed = speed;
	}

	/**
	 * Getter method for heading 
	 * @return heading 
	 */
	public int getHeading() {
		return this.heading;
	}

	/**
	 * Setter method for heading 
	 * @param heading
	 */
	public void setHeading(int heading) {
		if (heading >= 0 && heading <= 360)
			this.heading = heading;
	}
	
	/**
	 * Printer method for heading - useful for printing map 
	 * @return String of heading value 
	 */
	public String printHeading() {
		return "heading=" + this.heading;
	}

	/**
	 * Getter method for speed 
	 * @return speed 
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Setter method for speed 
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Printer method for speed - useful for printing map 
	 * @return string of speed value 
	 */
	public String printSpeed() {
		return "speed=" + this.speed;
	}
	
	/**
	 * Abstract method to move the movable game objects in their 
	 * own specialized way. Implementations are in child classes of
	 * MovableGameObject. Width and height are passed to keep track 
	 * of maximum X and Y values. 
	 * 
	 * @param heading
	 * @param speed
	 * @param width 
	 * @param height 
	 */
	public abstract void move(int heading, int speed, int width, int height);		
	
}
