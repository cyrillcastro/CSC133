package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for giving the about information. 
 * Extends from Command class.
 */
public class GiveAboutCommand extends Command {

	/**
	 * Constructs command object for giving the about information.
	 * Calls parent constructor with the command name. 
	 */
	public GiveAboutCommand() {
		super("About");
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * give about info command
	 * 
	 * Displays a dialog for name, Course info, Name and Version number of the game. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		String info = "Name: Cyrill Castro\nCourse: CSC 133 - Object Oriented Computer Graphics\nBugZ Game | Version: 2";
		
		boolean c = Dialog.show("About", info, "OK", null);
	}

}
