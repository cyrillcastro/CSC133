package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Flag class constructs Flag objects. Flags are fixed game objects
 * with a sequenceNumber. 
 */
public class Flag extends FixedGameObject {
	
	// private field exclusive for Flags
	private int sequenceNumber;
	
	// Use to call gameworld method 
	private GameWorld gw;
	
	/**
	 * Flag constructor, inherits from FixedGameObject. Also 
	 * instantiates sequenceNumber of the class.
	 * 
	 * @param x
	 * @param y
	 * @param sequenceNumber
	 */
	public Flag(GameWorld gw, float x, float y, int sequenceNumber) {
		super(105, x, y, 135, 206, 235);	
		this.sequenceNumber = sequenceNumber;
		
		this.gw = gw;
	}
	
	/**
	 * Flags cannot change color once created. Method overrides setColor
	 * from GameObject to fit this requirement. 
	 */
	@Override
	public void setColor(int r, int g, int b) { }

	/**
	 * Getter method for sequenceNumber.
	 * @return sequenceNumber
	 */
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	
	/**
	 * Setter method for sequenceNumber. 
	 * @param sequenceNumber
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	} 
	
	/**
	 * Overridden toString method to print Flag values for map. 
	 */
	@Override
	public String toString() {
		return "Flag: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " seqNum=" + this.sequenceNumber;
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
	 * Draw method for Flag. Draws a filled isosceles triangle and a string with its Flag Number. 
	 * 
	 * Should know about the different ways of drawing depending on "isSelected"
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

		// Create an array for each of the X and Y coordinates 
		int[] xPoints = {pCmpRelScrn.getX()+xTop, pCmpRelScrn.getX()+xBottomLeft, pCmpRelScrn.getX()+xBottomRight};
		int[] yPoints = {pCmpRelScrn.getY()+yTop, pCmpRelScrn.getY()+yBottomLeft, pCmpRelScrn.getY()+yBottomRight};
		g.setColor(this.getColor());
		
		if (this.isSelected() && gw.isPaused())
			g.drawPolygon(xPoints, yPoints, 3);
		else {
			g.fillPolygon(xPoints, yPoints, 3);
			this.setSelected(false);
		}
		
		// Draw the string to show the sequence number 
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.getSequenceNumber(), (int) (pCmpRelScrn.getX() + this.getLocation().getX()), (int) (pCmpRelScrn.getY() + this.getLocation().getY()));
	}
	
	/**
	 * Method that determines if a pointer is in an object 
	 */
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)  {
		return super.contains(pPtrRelPrnt, pCmpRelPrnt);
	}

	/**
	 * Method to handle collisions of Flags with various ICollider objects. Once done, add to each of the objects' vectors. 
	 */
	@Override
	public void handleCollision(GameObject otherObject) { 
		if (!this.getCollisions().contains(otherObject) && !otherObject.getCollisions().contains(this)) {
			if (otherObject instanceof Ant)
				gw.collideWithFlag(this);
		
			this.getCollisions().add(otherObject);
			otherObject.getCollisions().add(this);
		}
	}
	
}