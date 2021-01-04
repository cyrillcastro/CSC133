package com.mycompany.a3;

/**
 * ISteerable interface which allows for MovableGameObjects to 
 * be steered. 
 */
public interface ISteerable {
	
	/**
	 * Method to steer the MovableGameObject by passing an integer value to update
	 * the heading. Method is abstract so that the specific game object can implement it 
	 * in its own class. 
	 * @param h
	 */
	public abstract void steer(int h);

}
