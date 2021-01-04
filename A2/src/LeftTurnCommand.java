package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for "left turn" method of GameWorld. 
 * Extends from Command class, and uses GameWorld as a target.
 */
public class LeftTurnCommand extends Command {
	
	// Private field - to access method in GameWorld
	private GameWorld gw;

	/**
	 * Constructs command object for left turn.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes GameWorld as the target for the command. 
	 */
	public LeftTurnCommand(GameWorld gw) {
		super("Left Turn");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * left turn command (by invoking method in GameWorld) 
	 * 
	 * Also prints a message to the console to verify that method has 
	 * been called and game has been manipulated. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nPlayer Ant has turned 5 degrees to the left.");
		gw.turnLeft();
	}

}