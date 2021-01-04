package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for "collide with food station" method of GameWorld. 
 * Extends from Command class, and uses GameWorld as a target.
 */
public class FoodStationCollideCommand extends Command {
	
	// Private field - to access method in GameWorld
	private GameWorld gw;

	/**
	 * Constructs command object for collide with food station.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes GameWorld as the target for the command. 
	 */
	public FoodStationCollideCommand(GameWorld gw) {
		super("Collide with Food Station");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * collide with food station command (by invoking method in GameWorld) 
	 * 
	 * Also prints a message to the console to verify that method has 
	 * been called and game has been manipulated. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nPlayer Ant has collided with food station.");
		gw.collideWithFoodStation();
	}

}
