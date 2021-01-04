package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for giving the help information. 
 * Extends from Command class.
 */
public class GiveHelpCommand extends Command {

	/**
	 * Constructs command object for giving the help information.
	 * Calls parent constructor with the command name. 
	 */
	public GiveHelpCommand() {
		super("Help");
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * give help info command. 
	 * 
	 * Displays a dialog box with all of the key commands. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		String info = "To play 'BugZ Game', use the buttons on the screen or the following keys: \n'a' = Accelerate Player Ant\n'b' = Brake Player Ant\n'l' = Turn Player Ant Left\n'r' = Turn Player Ant Right\n'f' = Collide with FoodStation\n'g' = Collide with Spider\n't' = Tick the Clock\n'x' = Exit the Game";
		
		boolean c = Dialog.show("How to Play", info, "OK", null);
	}

}
