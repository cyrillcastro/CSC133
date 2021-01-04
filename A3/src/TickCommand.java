package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;

/**
 * Creates a command object for "tick clock" method of GameWorld. 
 * Extends from Command class, and uses GameWorld as a target.
 */
public class TickCommand extends Command {
	
	// Private field - to access method in GameWorld
	private GameWorld gw;

	/**
	 * Constructs command object for tick clock.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes GameWorld as the target for the command. 
	 */
	public TickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * tick command (by invoking method in GameWorld) 
	 * 
	 * Also prints a message to the console to verify that method has 
	 * been called and game has been manipulated. Once method has 
	 * been called, the Observable notifies its Observers that a change 
	 * has been made. 	 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nClock has ticked.");
		
		Dimension dCmpSize = new Dimension(gw.getWidth(), gw.getHeight());
		
		gw.tickClock(40, dCmpSize);
		gw.notifyObservers();
	}

}