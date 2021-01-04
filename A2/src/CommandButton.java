package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

/**
 * Class that creates and styles command buttons on the Game form. Extends from 
 * built-in Button class. 
 *
 */
public class CommandButton extends Button {
	
	/**
	 * Constructor method for Command Button. Styles the button by setting background 
	 * transparency, background color, foreground color, setting the border, and padding. 
	 */
	public CommandButton() {
		super();
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	
		this.getAllStyles().setPadding(Component.TOP, 5);
		this.getAllStyles().setPadding(Component.BOTTOM, 5);
	}

}
