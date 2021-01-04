package com.mycompany.a3;

import com.codename1.ui.geom.Point;

/**
 * FixedGameObject class constructs fixed game objects. Fixed game objects are 
 * game objects that cannot change their location once created. Because each 
 * FixedGameObject has their own specifications, this class is abstract.
 */
public abstract class FixedGameObject extends GameObject implements ISelectable {
	
	private boolean isSelected = false;
	
	/**
	 * FixedGameObject constructor. Inherits from GameObject constructor. 
	 * 
	 * @param size
	 * @param x
	 * @param y
	 * @param r
	 * @param g
	 * @param b
	 */
	public FixedGameObject(int size, float x, float y, int r, int g, int b) {
		super(size, x, y, r, g, b);
	}
	
	/**
	 * Sets boolean if the object is selected. 
	 */
	@Override
	public void setSelected(boolean yesNo) {
		this.isSelected = yesNo;
	}
	
	/**
	 * Returns if the object is selected. 
	 */
	@Override
	public boolean isSelected() {
		return this.isSelected;
	}
	
	/**
	 * Returns if the pointer is within the fixed game object
	 */
	@Override 
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// Pointer location relative to parent's origin
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		
		int xLoc = (int) (pCmpRelPrnt.getX() + this.getLocation().getX() - this.getSize()/2);
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getLocation().getY() - this.getSize()/2);
		
		if ((px >= xLoc) && (px <= xLoc + this.getSize()) && (py >= yLoc) && py <= (yLoc + this.getSize()))
			return true;
		else 
			return false;
	}
	
	
}