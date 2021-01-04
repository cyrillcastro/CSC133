package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

/**
 * MapView is a container that will display game objects and an observable by observer GameWorld. 
 */
public class MapView extends Container implements Observer {
	
	/**
	 * Constructor for MapView. Registers MapView as an observer to 
	 * the Observable that is passed. 
	 * @param o
	 */
	public MapView(Observable o) {
		o.addObserver(this);
	}

	/**
	 * Method to update the observer. Outputs the game object information to the console. Calls 
	 * outputMap method from GameWorld 
	 */
	@Override
	public void update(Observable observable, Object data) {
		System.out.print("\nOutputting current map...\n");
		((GameWorld) observable).outputMap();
	}

}
