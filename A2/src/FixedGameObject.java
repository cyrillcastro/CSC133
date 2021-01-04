package com.mycompany.a2;

/**
 * FixedGameObject class constructs fixed game objects. Fixed game objects are 
 * game objects that cannot change their location once created. Because each 
 * FixedGameObject has their own specifications, this class is abstract.
 */
public abstract class FixedGameObject extends GameObject {
	
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
	 * Overridden method from GameObject. Fixed game objects cannot change their
	 * location once created. 
	 */
	@Override
	public void setLocation(float x, float y) { }
	
}
