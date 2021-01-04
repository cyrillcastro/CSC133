package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * IDrawable interface to be implemented by GameObject. Defines the draw 
 * method that will create the graphics for each GameObject. 
 */
public interface IDrawable {

	public abstract void draw(Graphics g, Point pCmpRelPrnt);
}