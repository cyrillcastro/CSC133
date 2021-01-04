package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for setting the sound on or off. 
 * Extends from Command class.
 */
public class SetSoundCommand extends Command {
	
	// the target of the command 
	private GameWorld gw;
	
	/**
	 * Constructs command object for setting the sound.
	 * Calls parent constructor with the command name. 
	 * 
	 * Sets GameWorld as the target. 
	 */
	public SetSoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * set sound command. 
	 * 
	 * checks if the event from component is a checkbox being selected,
	 * if it is true, then call the GameWorld to set the sound to true. 
	 * Else, set it to false. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		if (((CheckBox) ev.getComponent()).isSelected()) {
			gw.setSound(true);
		} else {
			gw.setSound(false);
		}
	}

}