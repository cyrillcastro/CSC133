package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object to select Pause or Play mode for the game. 
 */
public class PauseCommand extends Command {
	
	private GameWorld gw; 
	
	/**
	 * Constructs command object for Pause/Play mode.
	 * 
	 * First calls parent constructor with command name, 
	 * then passes Game as the target for the command. 
	 */
	public PauseCommand(GameWorld gw, CommandButton button) {
		super("Pause");
		this.gw = gw;
	}
	
	/**
	 * Method to set Pause or Play mode for the game. If paused, 
	 * then the background sound is paused. Else, if the sound is on, 
	 * then the background sound starts from the beginning. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		gw.setPaused();		
		
		if (gw.isPaused())
			gw.getBgSound().pause();
		else 
			if (gw.soundOn())
				gw.getBgSound().run();
	}
}
