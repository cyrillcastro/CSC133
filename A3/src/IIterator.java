package com.mycompany.a3;

/**
 * Interface to implement the Iterator Pattern. Methods are abstract and
 * will be used for/implemented in GameObjectCollection class. 
 */
public interface IIterator {
	
	/**
	 * Will check whether or not there is an Object in the collection 
	 * after the current object and return the result. 
	 * @return boolean 
	 */
	public abstract boolean hasNext();
	
	/**
	 * Will return the next GameObject in the collection. 
	 * @return GameObject 
	 */
	public abstract GameObject getNext();
}
