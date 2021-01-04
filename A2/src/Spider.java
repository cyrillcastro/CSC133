package com.mycompany.a2;

/**
 * Spider class constructs Spider objects.
 * Spiders are movable game objects that cannot be steered. 
 */
public class Spider extends MovableGameObject {

	/**
	 * Spider constructor, which inherits from MovableGameObject class.
	 * 
	 * @param size
	 * @param x
	 * @param y
	 * @param heading
	 * @param speed
	 */
	public Spider(int size, float x, float y, int heading, int speed) {
		super(size, x, y, 0, 0, 0, heading, speed);	
	}
	
	
	/**
	 * Spiders cannot change color once created. Method overrides setColor
	 * from GameObject to fit this requirement. 
	 */
	@Override
	public void setColor(int r, int g, int b) { }
	
	/**
	 * Overridden method from MovableGameObject 
	 * Spiders move automatically, and when they hit the edge of the GameWorld, they
	 * are supposed to bounce. 
	 * 
	 * Convert (90 - heading) to radians. Take sine/cosine of this value, and 
	 * multiply it by the speed. Equation given in assignment specifications. 
	 * 
	 * Check if the newX is less than 0.0 or greater than 1024.0. If so, switch the heading
	 * of the Spider by 180 degrees (through adding or subtracting), and update deltaX based 
	 * on this new heading. Do the same for newY. Then call setLocation. 
	 */
	@Override
	public void move(int heading, int speed, int width, int height) {
		float deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		if (newX <= 0.0 || newX >= width) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newX = this.getLocation().getX() + deltaX;
			newY = this.getLocation().getY() + deltaY;
		}
		
		if (newY <= 0.0 || newY >= height) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newX = this.getLocation().getX() + deltaX;
			newY = this.getLocation().getY() + deltaY;
		}
		
		this.setLocation(newX, newY);
	}
	
	/**
	 * toString method to print Spider values for map. 
	 */
	@Override
	public String toString() {
		return "Spider: " + this.printLocation() + " " + this.printColor() + " " + this.printHeading() + " " + this.printSpeed() + " " + this.printSize();
	}

}
