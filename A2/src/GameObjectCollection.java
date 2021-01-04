package com.mycompany.a2;

import java.util.ArrayList;

/**
 * GameObjectCollection class to create a collection to keep tract of all of the
 * GameObjects, and implements ICollection to manipulate the collection as needed
 * for the game. 
 */
public class GameObjectCollection implements ICollection {
	
	// private field, holding the data structure of GameObjectCollection.
	// ArrayList will be used to implement/manipulate the collection. 
	private ArrayList<GameObject> gameObjColl;
	
	/**
	 * Constructor of GameObjectCollection. Initializes the private
	 * field ArrayList. 
	 */
	public GameObjectCollection() {
		gameObjColl = new ArrayList<GameObject>();
	}
	
	/**
	 * Method to add new objects into the collection. 
	 * 
	 * New object is passed as a parameter when method is called,
	 * program checks if the new object is a GameObject. If so,
	 * the GameObject is added to the collection. 
	 */
	@Override
	public void add(Object newObject) {
		if (newObject instanceof GameObject)
			gameObjColl.add((GameObject) newObject);
	}
	
	/**
	 * Method to return the iterator of the GameObjectCollection. 
	 * Returns a new iterator object for the collection. 
	 */
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	/**
	 * Method to clear the GameObjectCollection 
	 */
	public void clear() {
		gameObjColl.clear();		
	}
	
	/**
	 * This private class implements the IIterator interface and creates
	 * the Iterator for GameObjectCollection. It is supposed to keep track
	 * of the current index. 
	 */
	private class GameObjectIterator implements IIterator {
		
		// Private field to hold current index
		private int currentIndex;
		
		/**
		 * Constructor for GameObjectIterator. Initializes current index to
		 * -1 and increments as new GameObjects are added to the collection.
		 */
		public GameObjectIterator() {
			currentIndex = -1;
		}
		
		public int getCurrentIndex() {
			return this.currentIndex;
		}
		
		/**
		 * Method to check whether or not the current element 
		 * is the last element. Uses the current index and size()
		 * method of ArrayList. 
		 * 
		 * If the size of the collection is less than or equal to 0, 
		 * there is no next. Return false. 
		 * 
		 * If the currentIndex is on the last index of the entire 
		 * collection, there is no next. Return false. 
		 * 
		 * Otherwise, return true. 
		 */
		@Override
		public boolean hasNext() {
			if (gameObjColl.size() <= 0)
				return false;
			
			if (currentIndex == gameObjColl.size() - 1)
				return false;
			
			return true;
		}

		/**
		 * Method to get the next element of the collection. 
		 * 
		 * Current index increases by one, then obtains the object 
		 * at the new current index through get() method of ArrayList. 
		 */
		@Override
		public GameObject getNext() {
			currentIndex ++;
			return (gameObjColl.get(currentIndex));
		}
		
		public GameObject checkNext() {
			return gameObjColl.get(currentIndex + 1);
		}
		
	} // end private Iterator class 
	
} 
