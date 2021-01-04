package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

/**
 * Spider class constructs Spider objects.
 * Spiders are movable game objects that cannot be steered. 
 */
public class Spider extends MovableGameObject {
	
	// Use this to call the GameWorld collision method 
	private GameWorld gw;
	
	/**
	 * Spider constructor, which inherits from MovableGameObject class.
	 * 
	 * @param size
	 * @param x
	 * @param y
	 * @param heading
	 * @param speed
	 */
	public Spider(GameWorld gw, int size, float x, float y, int heading, int speed) {
		super(size, x, y, 0, 0, 0, heading, speed);	
		
		this.gw = gw;
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
	 * Check if the newX is less than 0.0 or greater than MapView container. If so, switch the heading
	 * of the Spider by 180 degrees (through adding or subtracting), and update deltaX based 
	 * on this new heading. Do the same for newY. Then call setLocation. 
	 */
	@Override
	public void move(int elapsed, Dimension dCmpSize) {		
		int dist = this.getSpeed() * elapsed/1000;
		
		float deltaX = (float) (Math.cos(Math.toRadians(90 - this.getHeading())) * dist);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - this.getHeading())) * dist);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		int currentHeading = this.getHeading();
		
		if ((newX + this.getSize()/2 >= dCmpSize.getWidth()) || (newX - this.getSize()/2 < 0.0) || (newY + this.getSize()/2 >= dCmpSize.getHeight()) || (newY - this.getSize()/2 < 0.0)) {
			
			if (newX + this.getSize()/2 >= dCmpSize.getWidth()) 
				newX = dCmpSize.getWidth() - this.getSize()/2;
			else if (newX - this.getSize()/2 < 0.0) 
				newX = (float) 0.0 + this.getSize()/2;
			else if (newY + this.getSize()/2 >= dCmpSize.getHeight())
				newY = dCmpSize.getHeight() - this.getSize()/2;
			else if (newY - this.getSize()/2 < 0.0) 
				newY = (float) 0.0 + this.getSize()/2;
			
			// Working on new bounce technology
			/*if (currentHeading <= 90) {
				int reflect = 90 - currentHeading;
				this.setHeading(90 + reflect);
			} else if (currentHeading <= 180 && currentHeading > 90) {
				int reflect = 180 - currentHeading;
				this.setHeading(reflect);
			} else if (currentHeading <= 270 && currentHeading > 180) {
				int reflect = 270 - currentHeading;
				this.setHeading(270 + reflect);
			} else {
				int reflect = 360 - currentHeading;
				this.setHeading(180 + reflect);
			}*/
			
			// Use this to keep the spiders in MapView
			this.setHeading(180 + currentHeading);
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

	/**
	 * Draw method for Spider. Draws an unfilled isosceles triangle.
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelScrn) {
		// Top corner coordinates 
		int xTop = (int) this.getLocation().getX();
		int yTop = (int) this.getLocation().getY() + this.getSize()/2;
		
		// Bottom left coordinates 
		int xBottomLeft = (int) this.getLocation().getX() - this.getSize()/2;
		int yBottomLeft = (int) this.getLocation().getY() - this.getSize()/2;
		
		// Bottom right coordinates 
		int xBottomRight = (int) this.getLocation().getX() + this.getSize()/2;
		int yBottomRight = (int) this.getLocation().getY() - this.getSize()/2;
		
		int[] xPoints = {pCmpRelScrn.getX()+xTop, pCmpRelScrn.getX()+xBottomLeft, pCmpRelScrn.getX()+xBottomRight};
		int[] yPoints = {pCmpRelScrn.getY()+yTop, pCmpRelScrn.getY()+yBottomLeft, pCmpRelScrn.getY()+yBottomRight};
		g.setColor(this.getColor());
		g.drawPolygon(xPoints, yPoints, 3);		
	}

	/**
	 * Method to handle collisions of various ICollider objects. Once handled, add to each of the objects' vectors
	 */
	@Override
	public void handleCollision(GameObject otherObject) {
		if (!this.getCollisions().contains(otherObject) && !otherObject.getCollisions().contains(this)) {
			if (otherObject instanceof Ant) 
				gw.collideWithSpider();
			
			this.getCollisions().add(otherObject);
			otherObject.getCollisions().add(this);
		}
	}

}
