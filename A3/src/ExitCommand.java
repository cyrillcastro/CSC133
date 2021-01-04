package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for "exit" method of GameWorld. 
 * Extends from Command class, and uses GameWorld as a target.
 */
public class ExitCommand extends Command {
	
	// Private field - to access method in GameWorld
	private GameWorld gw;

	/**
	 * Constructs command object for exit.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes GameWorld as the target for the command. 
	 */
	public ExitCommand(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method. Displays a 
	 * dialog box prompting the user to confirm their exit
	 * request. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		String exitPrompt = "Would you like to exit the game?";
		
		boolean yesExit = Dialog.show("Confirm Exit", exitPrompt, "Yes", "No");
		
		if (yesExit)
			System.exit(0);
	}

}