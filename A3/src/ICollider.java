package com.mycompany.a3;

/**
 * Interface to define detections and responses when collisions among GameObjects occur
 */
public interface ICollider {
	
	/**
	 * Method to apply appropriate detection algorithm 
	 * @param otherObject
	 * @return boolean
	 */
	public boolean collidesWith(GameObject otherObject);
	
	/**
	 * Method to apply appropriate response algorithm 
	 * @param otherObject
	 */
	public void handleCollision(GameObject otherObject);
}
