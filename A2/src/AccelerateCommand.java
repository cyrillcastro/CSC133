package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for "accelerate" method of GameWorld. 
 * Extends from Command class, and uses GameWorld as a target.
 */
public class AccelerateCommand extends Command {
	
	// Private field - to access method in GameWorld
	private GameWorld gw;

	/**
	 * Constructs command object for accelerate.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes GameWorld as the target for the command. 
	 */
	public AccelerateCommand(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * accelerate command (by invoking method in GameWorld) 
	 * 
	 * Also prints a message to the console to verify that method has 
	 * been called and game has been manipulated. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nPlayer Ant has accelerated.");
		gw.accelerate();
	}

}