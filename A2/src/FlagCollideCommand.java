package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for "collide with flag" method of GameWorld. 
 * Extends from Command class, and uses GameWorld as a target.
 */
public class FlagCollideCommand extends Command {
	
	// Private field - to access method in GameWorld
	private GameWorld gw;

	/**
	 * Constructs command object for collide with flag.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes GameWorld as the target for the command. 
	 */
	public FlagCollideCommand(GameWorld gw) {
		super("Collide with Flag");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * collide with flag command (by invoking method in GameWorld). 
	 * 
	 * Displays a dialog box prompting for the flag number. Checks if the 
	 * input is null or empty string, then shows a notice dialog box for the 
	 * player. 
	 * 
	 * Otherwise, the program tries to parse the input String into a number. If cannot
	 * be parsed or greater than the number of Flag objects in the game, then a notice 
	 * Dialog box pops up.
	 * 
	 * Otherwise, the input is parsed into an integer, then calls collideWithFlag method from 
	 * gameworld. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		Command cOk = new Command("OK");
		Command cCancel = new Command("Cancel");
		Command [] cmds = new Command[] {cOk, cCancel};
		TextField myTF = new TextField();
		
		int playerInputInt = gw.getPlayer().getLastFlagReached(); 	// initial value will be last base reached 
		String playerInputStr;
		
		Command c = Dialog.show("Enter Flag Number: ", myTF, cmds);
		
		if (c == cOk) {
			playerInputStr = myTF.getText();
			
			if (playerInputStr == null || playerInputStr.length() == 0) {
				boolean notice = Dialog.show("Notice", "Invalid input. Please enter a number 1-5.", "OK", null);
			} else {
				try {
					playerInputInt = Integer.parseInt(playerInputStr);
					
					if (playerInputInt >= gw.getLastFlagNum()) {
						boolean numRangeNotice = Dialog.show("Notice", "Invalid input. Please enter a number 1-5. There are only 5 flags in the game.", "OK", null);
					}

				} catch (NumberFormatException e) {
					boolean notice = Dialog.show("Notice", "Invalid input. Please enter a number 1-5.", "OK", null);
				}
	
				gw.collideWithFlag(playerInputInt);
			}
			
		}
		
			
	}

}
