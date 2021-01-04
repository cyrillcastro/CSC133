package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Creates a command object for the "position" functionality for 
 * ISelectable objects. 
 *
 */
public class PositionCommand extends Command {
	
	private GameWorld gw;
	
	/**
	 * Constructor for the Position Command
	 * @param gw
	 */
	public PositionCommand(GameWorld gw) {
		super("Position");
		this.gw = gw;
	}
	
	/**
	 * Method to invoke the position method from GameWorld. If the 
	 * GameWorld is pause, then allow repositioning 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		if (gw.isPaused()) {
			gw.reposition();
		}
	}

}
