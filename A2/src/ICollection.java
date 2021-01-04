package com.mycompany.a2;

/**
 * Interface to define the methods of GameObjectCollection according
 * to the Iterator pattern.
 * Methods will be implemented in GameObjectCollection 
 *
 */
public interface ICollection {
	
	/**
	 * Method to add objects to the collection
	 */
	public abstract void add(Object newObject);
	
	/**
	 * Method to return the IIterator
	 * @return IIterator
	 */
	public abstract IIterator getIterator();
	
}
