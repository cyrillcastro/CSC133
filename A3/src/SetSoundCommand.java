package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for setting the sound on or off. 
 * Extends from Command class.
 */
public class SetSoundCommand extends Command {
	
	// the targets of the command 
	private GameWorld gw;
	
	/**
	 * Constructs command object for setting the sound.
	 * Calls parent constructor with the command name. 
	 * 
	 * Sets GameWorld as the target. 
	 */
	public SetSoundCommand(GameWorld gw, Game g) {
		super("Sound");
		this.gw = gw;
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * set sound command. 
	 * 
	 * checks if the event from component is a CheckBox being selected,
	 * if it is true, then call the GameWorld to set the sound to true
	 * and run the sound from the beginning. 
	 * 
	 * Else, set it to false and pause the sound. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		if (((CheckBox) ev.getComponent()).isSelected()) {
			gw.setSound(true);
			gw.getBgSound().run();
		} else {
			gw.setSound(false);
			gw.getBgSound().pause();
		}
	}

}