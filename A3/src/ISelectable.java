package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Interface to specify methods required for object selection  
 */
public interface ISelectable {

	/**
	 * A method to mark an object as "selected" or not 
	 * 
	 * @param yesNo
	 */
	public void setSelected(boolean yesNo);
	
	/**
	 * A method to test whether an object is selected 
	 * 
	 * @return boolean
	 */
	public boolean isSelected();
	
	/**
	 * A method to determine if a pointer is "in" an object 
	 * pPtrRelPrnt is pointer position relative to the parent origin 
	 * pCmpRelPrnt is the component position relative to the parent origin 
	 * 
	 * @param pPtrRelPrnt
	 * @param pCmpRelPrnt
	 * @return
	 */
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	/**
	 * A method to "draw" the object that knows about drawing different
	 * ways depending on "isSelected"
	 * 
	 * @param g
	 * @param pCmpRelPrnt
	 */
	public void draw(Graphics g, Point pCmpRelPrnt);
	
}
